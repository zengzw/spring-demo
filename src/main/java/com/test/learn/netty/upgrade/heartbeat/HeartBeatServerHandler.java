/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatServerHandler.java
 * @Package Name: com.test.learn.netty.upgrade.heartbeat
 * @Date: 2018年3月7日上午9:47:02
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.upgrade.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * @author zengzw-1220
 * @date 2018年3月7日上午9:47:02
 * @see
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channer service read......");
		System.out.println(ctx.channel().remoteAddress()+"->Server:"+msg.toString());
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		System.out.println("------出错了-----"+ctx.channel().remoteAddress());
		ctx.close();
	}

	
}