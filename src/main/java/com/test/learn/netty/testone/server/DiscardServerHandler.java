/*
 * @Project Name: springmvcdemo
 * @File Name: DiscardServerHandler.java
 * @Package Name: com.test.learn.netty.testone.server
 * @Date: 2017年9月28日下午3:22:41
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.testone.server;

import com.test.learn.netty.testone.entity.UnixTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午3:22:41
 * @see
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	//	System.out.println("----channel read");
		/*ByteBuf buf = (ByteBuf)msg;
		try {
			while (buf.isReadable()) { // (1)
				System.out.print((char) buf.readByte());
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(msg); // (2)
		}*/
		
		//ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
	}



	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("---active");

		ChannelFuture future = ctx.writeAndFlush(new UnixTime());
		future.addListener(ChannelFutureListener.CLOSE);
	}


	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

		System.out.println("registered.....");
		super.channelRegistered(ctx);
	}


	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

		System.out.println("un registered......");
		super.channelUnregistered(ctx);
	}


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		//System.out.println("read complete......");
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

		System.out.println("writability changed....");
		super.channelWritabilityChanged(ctx);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("exception............");
		cause.printStackTrace();
		ctx.close();
	}
}
