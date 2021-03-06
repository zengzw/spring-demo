/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatServer.java
 * @Package Name: com.test.learn.netty.heartbeat
 * @Date: 2017年11月2日上午9:47:19
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.heartbeat;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月2日上午9:47:19
 * @see
 */
public class HeartBeatServer {

	int port;

	public HeartBeatServer(int port){
		this.port = port;
	}


	public void start(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap()
				.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.localAddress(new InetSocketAddress(port))  
				.childHandler(new HeartBeatServerChannelInitializer())
				.childOption(ChannelOption.SO_KEEPALIVE, true);  

		try {
			// 服务器绑定端口监听  
			ChannelFuture future = bootstrap.bind(port).sync();
			System.out.println("Server start listen at:"+port);
			// 监听服务器关闭，此方法会阻塞  
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}finally {

		}
	}

	public static void main(String[] args) {
		HeartBeatServer server = new HeartBeatServer(7788);
		server.start();
	}
}
