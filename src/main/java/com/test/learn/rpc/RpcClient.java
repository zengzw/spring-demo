/*
 * @Project Name: springmvcdemo
 * @File Name: RpcClient.java
 * @Package Name: com.test.learn.rpc
 * @Date: 2017年9月26日上午10:00:53
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc;

import com.test.learn.rpc.decode.RpcDecoder;
import com.test.learn.rpc.decode.RpcEncoder;
import com.test.learn.rpc.vo.RpcRequest;
import com.test.learn.rpc.vo.RpcResponse;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月26日上午10:00:53
 * @see
 */
public class RpcClient  extends SimpleChannelInboundHandler<RpcResponse> {

	private String host;

	private int port;

	private RpcResponse response;

	private final Object obj = new Object();


	public RpcClient(String host,int port) {
		this.host = host;
		this.port = port;
	}



	@Override
	protected void channelRead0(ChannelHandlerContext context, RpcResponse response) throws Exception {
		this.response = response;

		synchronized (obj) {
			obj.notifyAll(); //收到响应，唤醒线程
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}


	/**
	 * 发送请求
	 * TODO
	 * @date 2017年9月26日上午10:05:15
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param request
	 * @return
	 * @throws InterruptedException 
	 */
	public RpcResponse send(RpcRequest request) throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline()
					.addLast(new RpcEncoder(RpcRequest.class)) // 将 RPC 请求进行编码（为了发送请求）
					.addLast(new RpcDecoder(RpcResponse.class)) // 将 RPC 响应进行解码（为了处理响应）
					.addLast(RpcClient.this); // 使用 RpcClient 发送 RPC 请求
				}
			})
			.option(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootstrap.connect(host,port).sync();
			future.channel().writeAndFlush(request).sync();
			
			synchronized (obj) {
				obj.wait(); // 未收到响应，使线程等待
			}
			
			if(response != null){
				future.channel().closeFuture().sync();
			}
			
			return response;
		} finally{
			group.shutdownGracefully();
		}
	}
}
