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

import com.test.learn.rpc.util.SerializationUtil;

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

	private Class<?> genericClass;

	public RpcDecoder(Class<?> genericClass) {
		this.genericClass = genericClass;
	}


	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 4) {
			return;
		}
		in.markReaderIndex();
		int dataLength = in.readInt();
		if (dataLength < 0) {
			ctx.close();
		}
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
			return;
		}
		byte[] data = new byte[dataLength];
		in.readBytes(data);

		Object obj = SerializationUtil.deserialize(data, genericClass);
		out.add(obj);
	}


}
