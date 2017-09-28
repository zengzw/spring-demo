/*
 * @Project Name: springmvcdemo
 * @File Name: TimeEncode.java
 * @Package Name: com.test.learn.netty.testone.decode
 * @Date: 2017年9月28日下午4:46:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.testone.decode;

import com.test.learn.netty.testone.entity.UnixTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午4:46:49
 * @see
 */
public class TimeEncode extends ChannelOutboundHandlerAdapter{
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		UnixTime time = (UnixTime)msg;
		ByteBuf buf = ctx.alloc().buffer(4);
		buf.writeInt((int)time.value());
		ctx.write(buf,promise);
	
	}
}
