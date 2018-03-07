/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatServerChannelInitializer.java
 * @Package Name: com.test.learn.netty.heartbeat
 * @Date: 2017年11月2日上午9:51:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.heartbeat;



import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 
 * @author zengzw-1220
 * @date 2017年11月2日上午9:51:50
 * @see
 */
public class HeartBeatServerChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//		读超时时间为5s，写超时和读写超时为0，然后加入时间控制单元。如果5秒内没有收到消息，将会触发HeartBeatServerHandler.userEventTriggered()的方法，计数到达最大数，关闭客户端链接。
		pipeline.addLast("handler",new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
		.addLast("encode",new StringEncoder	())
		.addLast("deocde",new StringDecoder())
		.addLast(new HeartBeatServerHandler());


	}


}
