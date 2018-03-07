/*
 * @Project Name: springmvcdemo
 * @File Name: ConnectorIdleStateTrigger.java
 * @Package Name: com.test.learn.netty.upgrade.heartbeat.client
 * @Date: 2018年3月7日上午11:19:07
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.upgrade.heartbeat.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * T
 * @author zengzw-1220
 * @date 2018年3月7日上午11:19:07
 * @see
 */
@Sharable
public class ConnectorIdleStateTrigger extends ChannelInboundHandlerAdapter{

	private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",  
			CharsetUtil.UTF_8));  

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

		if(evt instanceof IdleStateEvent){
			IdleState state = ((IdleStateEvent)evt).state();
			if (state == IdleState.WRITER_IDLE) {  
				// write heartbeat to server  
				ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());  
			}  
		}else{
			super.userEventTriggered(ctx, evt);
		}
	}
}
