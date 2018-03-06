/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatClient.java
 * @Package Name: com.test.learn.netty.heartbeat.client
 * @Date: 2017年11月2日上午10:08:54
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.heartbeat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 
 * @author zengzw-1220
 * @date 2017年11月2日上午10:08:54
 * @see
 */
public class HeartBeatClient {

	static int port;

	static String address;

	static Bootstrap bootstrap = null;

	public HeartBeatClient(){};

	public HeartBeatClient(int port,String address){
		this.port = port;
		this.address = address;
	}

	public void start(){
		EventLoopGroup group = new NioEventLoopGroup();
		ChannelFuture future = null;
		bootstrap = new Bootstrap();
		bootstrap.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)  
		.handler(new LoggingHandler(LogLevel.INFO))  
		.handler(new HeartBeatsClientChannelInitializer());

		try {
			future = bootstrap.connect(address,port).sync();
			future.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (null != future) {  
				if (future.channel() != null && future.channel().isOpen()) {  
					future.channel().close();  
				}  
			}  
			System.out.println("----connectting......");
			this.start();
			System.out.println("----connectting sucess......");
		}

	}


	public static void doConnect() throws InterruptedException{
		System.out.println("----connectting......");

	}


	public static void main(String[] args) {
		HeartBeatClient client = new HeartBeatClient(7788,"127.0.0.1");
		client.start();
	}
}
