/*
 * @Project Name: springmvcdemo
 * @File Name: TimeDecoder.java
 * @Package Name: com.test.learn.netty.testone.decode
 * @Date: 2017年9月28日下午4:38:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.testone.decode;

import java.util.List;

import com.test.learn.netty.testone.entity.UnixTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午4:38:50
 * @see
 */
public class TimeDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

		if (in.readableBytes() < 4) {
			return; // (3)
		}

	//	out.add(in.readBytes(4)); // (4)
		
		out.add(new UnixTime(in.readUnsignedInt()));
	}
}
