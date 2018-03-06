/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatsClientChannelInitializer.java
 * @Package Name: com.test.learn.netty.heartbeat.client
 * @Date: 2017年11月2日上午10:13:15
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.heartbeat.client;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 *  客户端设置写超时，3秒；超时会触发 HeartBeatClientHandler.userEventTriggered()方法 
 * @author zengzw-1220
 * @date 2017年11月2日上午10:13:15
 * @see
 */
public class HeartBeatsClientChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//		写超时为3秒，客户端执行的动作为写消息到服务端，服务端执行读动作。
		pipeline.addLast("handler",new IdleStateHandler(0, 3, 0, TimeUnit.SECONDS))
		.addLast("encode",new StringEncoder())
		.addLast("decode",new StringEncoder())
		.addLast(new HeartBeatClientHandler());
	}
}
