/*
 * @Project Name: springmvcdemo
 * @File Name: TimeEncode1.java
 * @Package Name: com.test.learn.netty.testone.decode
 * @Date: 2017年9月28日下午4:50:54
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
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午4:50:54
 * @see
 */
public class TimeEncode1 extends MessageToByteEncoder<UnixTime>{

	@Override
	protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {

		out.writeByte((int)msg.value());
	}
}
