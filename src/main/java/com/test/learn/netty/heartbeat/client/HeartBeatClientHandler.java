/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatClientHandler.java
 * @Package Name: com.test.learn.netty.heartbeat.client
 * @Date: 2017年11月2日上午10:16:14
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.heartbeat.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月2日上午10:16:14
 * @see
 */
public class HeartBeatClientHandler   extends ChannelInboundHandlerAdapter{
	SimpleDateFormat format = new  SimpleDateFormat("yyyy-MM-dd HH:ss:mm");

	private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",
			CharsetUtil.UTF_8));

	private static final int TRY_TIMES = 3;

	private int currentTime = 0;



	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("激活时间是："+format.format(new Date()));
		System.out.println("链接已经激活...channelActive");

		ctx.writeAndFlush("hello server");
		//		如果我们捕获了一个事件, 并且想让这个事件继续传递下去, 那么需要调用 Context 相应的传播方法.
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("停止时间是："+format.format(new Date()));
		System.out.println("关闭链接 :channelInactive");

		//HeartBeatClient.doConnect();

		currentTime = 0;
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("当前轮询时间："+format.format(new Date()));

		if(evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent)evt;
			
			if(event.state() ==  IdleState.WRITER_IDLE){
				if(currentTime <= TRY_TIMES){
					currentTime ++;

					System.out.println("currentTime : "+currentTime);
					
					ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
				}
			}
		}
	}


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String) msg;
		System.out.println("receive server msg:"+message);

		if(message.equals("Heartbeat")){
			ctx.writeAndFlush("读到服务器发送的消息....");
			ctx.flush();
		}

		ReferenceCountUtil.release(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
