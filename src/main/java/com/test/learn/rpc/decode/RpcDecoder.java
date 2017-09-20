/*
 * @Project Name: springmvcdemo
 * @File Name: RpcDecoder.java
 * @Package Name: com.test.learn.rpc.decode
 * @Date: 2017年9月2日上午9:35:17
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.decode;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月2日上午9:35:17
 * @see
 */
public class RpcDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		
	}

	
}
