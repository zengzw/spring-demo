/*
 * @Project Name: springmvcdemo
 * @File Name: AcceptorIdleStateTrigger.java
 * @Package Name: com.test.learn.netty.upgrade.heartbeat
 * @Date: 2018年3月7日上午9:44:09
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.upgrade.heartbeat;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月7日上午9:44:09
 * @see
 */
@Sharable
public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter{

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("-------------Server UserEventTriggered");
		
		if(evt instanceof IdleStateEvent){
			IdleState state = ((IdleStateEvent)evt).state();
			
			if(state == IdleState.READER_IDLE){
				throw new Exception("=======idle exception=====");
			}
		}else{
			super.userEventTriggered(ctx, evt);
		}
	}
}
