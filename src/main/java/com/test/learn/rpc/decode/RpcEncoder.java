/*
 * @Project Name: springmvcdemo
 * @File Name: RpcEncoder.java
 * @Package Name: com.test.learn.rpc.decode
 * @Date: 2017年9月21日下午6:03:37
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.decode;

import com.test.learn.rpc.util.SerializationUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月21日下午6:03:37
 * @see
 */
public class RpcEncoder extends MessageToByteEncoder{
	private Class<?> genericClass;

	
    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    
    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            byte[] data = SerializationUtil.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
