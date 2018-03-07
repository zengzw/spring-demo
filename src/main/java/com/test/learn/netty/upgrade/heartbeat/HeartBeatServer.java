/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatServer.java
 * @Package Name: com.test.learn.netty.upgrade.heartbeat
 * @Date: 2018年3月7日上午9:43:42
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.upgrade.heartbeat;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 服务端
 * 
 * 
 * @author zengzw-1220
 * @date 2018年3月7日上午9:43:42
 * @see
 */
public class HeartBeatServer {

	private final AcceptorIdleStateTrigger idleStateTrigger = new AcceptorIdleStateTrigger();

	private int port;

	public HeartBeatServer(int port) {
		this.port = port;
	}


	public void start(){
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try{

			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workGroup)
			.channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new IdleStateHandler(5,0,0,TimeUnit.SECONDS));
					pipeline.addLast(idleStateTrigger);
					pipeline.addLast("decoder",new StringDecoder());
					pipeline.addLast("encoder",new StringEncoder());
					pipeline.addLast(new HeartBeatServerHandler());

				}

			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true); 

			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			System.out.println("server start listen at "+port);

			channelFuture.channel().closeFuture().sync();	

		}catch(Exception exception){
			exception.printStackTrace();

			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}



	public static void main(String[] args) {

		new HeartBeatServer(8080).start();
	}
}
