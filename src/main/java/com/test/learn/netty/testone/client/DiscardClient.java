/*
 * @Project Name: springmvcdemo
 * @File Name: DiscardClient.java
 * @Package Name: com.test.learn.netty.testone.client
 * @Date: 2017年9月28日下午4:22:20
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.testone.client;

import com.test.learn.netty.testone.decode.TimeDecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午4:22:20
 * @see
 */
public class DiscardClient {
	
	public static void main(String[] args) throws InterruptedException {

			EventLoopGroup workGroup = new NioEventLoopGroup();
			
			
			try{
					Bootstrap bootstrap = new Bootstrap();
					bootstrap.group(workGroup)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.handler(new ChannelInitializer<SocketChannel>(){

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline()
							.addLast(new TimeDecoder())
							.addLast(new ClientHandler());
						}
						
					});
					
					
					ChannelFuture future = bootstrap.connect("127.0.0.1", 8899);
					future.channel().closeFuture().sync();
					
			}finally{
				workGroup.shutdownGracefully();
			}
	}
}
