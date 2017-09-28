/*
 * @Project Name: springmvcdemo
 * @File Name: DiscardServer.java
 * @Package Name: com.test.learn.netty.testone.server
 * @Date: 2017年9月28日下午3:26:08
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.testone.server;

import com.test.learn.netty.testone.decode.TimeEncode;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午3:26:08
 * @see
 */
public class DiscardServer {
	private int port;

	public DiscardServer(int port) {
		this.port = port;
	}
	
	
	public void run() throws Exception{
//		是用来处理I/O操作的多线程事件循环器.第一个经常被叫做‘boss’，用来接收进来的连接。第二个经常被叫做‘worker’，用来处理已经被接收的连接，一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					System.out.println("---init-channel");
					ch.pipeline()
					.addLast(new TimeEncode())
					.addLast(new DiscardServerHandler());
				}
				
			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE,true);
			
			
			ChannelFuture future = bootstrap.bind(port).sync();   // 绑定端口，开始接收进来的连接
			
			 // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
			future.channel().closeFuture().sync();
			
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {

		new DiscardServer(8899).run();
	}
}
