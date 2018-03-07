/*
 * @Project Name: springmvcdemo
 * @File Name: HeartBeatClientHandler.java
 * @Package Name: com.test.learn.netty.upgrade.heartbeat.client
 * @Date: 2018年3月7日上午11:29:30
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.upgrade.heartbeat.client;

import java.util.Date;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月7日上午11:29:30
 * @see
 */
@Sharable  
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter{
	
	 @Override  
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
	        System.out.println("激活时间是："+new Date());  
	        System.out.println("HeartBeatClientHandler channelActive");  
	        ctx.fireChannelActive();  
	    }  
	  
	    @Override  
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception {  
	        System.out.println("停止时间是："+new Date());  
	        System.out.println("HeartBeatClientHandler channelInactive");  
	    }  
	  
	  
	    @Override  
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
	        String message = (String) msg;  
	        System.out.println(message);  
	        if (message.equals("Heartbeat")) {  
	            ctx.write("has read message from server:"+msg.toString());  
	            ctx.flush();  
	        }  
	        ReferenceCountUtil.release(msg);  
	    }  
}
