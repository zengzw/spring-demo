/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatHandler.java
 * @Package Name: com.test.learn.netty.heartbeat
 * @Date: 2017年11月2日上午10:02:40
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 
 * @author zengzw-1220
 * @date 2017年11月2日上午10:02:40
 * @see
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter{
	
	 private int loss_connect_time = 0;
	 
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() +" Server : "+msg.toString());
		
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent){
			   //服务端对应着读事件，当为READER_IDLE时触发
			IdleStateEvent event = (IdleStateEvent) evt;
			if(event.state() == IdleState.READER_IDLE){
				loss_connect_time ++;
				System.out.println("接受消息超时.......");
				
				if(loss_connect_time > 2){
					System.out.println("超时超过指定次数，关闭不要要的链接");
					ctx.channel().close();
					loss_connect_time = 0;
				}
			}else{
				
				super.userEventTriggered(ctx, evt);
			}
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
