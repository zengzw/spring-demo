/*
 * @Project Name: springmvcdemo
 * @File Name: ClientHandler.java
 * @Package Name: com.test.learn.netty.testone.client
 * @Date: 2017年9月28日下午4:32:22
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.testone.client;

import com.test.learn.netty.testone.entity.UnixTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午4:32:22
 * @see
 */
public class ClientHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/* ByteBuf m = (ByteBuf) msg; // (1)

	    try {

            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();

        } finally {
            m.release();
        }*/

		UnixTime m = (UnixTime) msg;
		System.out.println(m);
		ctx.close();
	}
}
