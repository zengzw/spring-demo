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

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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

	   bootstrap = new Bootstrap();
		bootstrap.group(group)
		.channel(NioSocketChannel.class)
		.handler(new HeartBeatsClientChannelInitializer());

		try {
			
			doConnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}

	}

	
	public static void doConnect() throws InterruptedException{
		System.out.println("----connectting......");
		ChannelFuture future = bootstrap.connect(address,port).sync();
		future.channel().closeFuture().sync();
	}
	
	
	public static void main(String[] args) {
		HeartBeatClient client = new HeartBeatClient(7788,"127.0.0.1");
		client.start();
	}
}