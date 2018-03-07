/*
 * @Project Name: springmvcdemo
 * @File Name: ChannelHandlerHolder.java
 * @Package Name: com.test.learn.netty.upgrade.heartbeat
 * @Date: 2018年3月7日上午9:40:28
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.upgrade.heartbeat;

import io.netty.channel.ChannelHandler;

/**
 *
 *  
 * 客户端的ChannelHandler集合，由子类实现，这样做的好处： 
 * 
 * 继承这个接口的所有子类可以很方便地获取ChannelPipeline中的Handlers 、
 * 
 * 获取到handlers之后方便ChannelPipeline中的handler的初始化和在重连的时候也能很方便地获取所有的handlers 
 * 
 * @author zengzw-1220
 * @date 2018年3月7日上午9:40:28
 * @see
 */
public interface ChannelHandlerHolder {
	
	/**
	 * 
	 * 所有Handler集合
	 * 
	 * @date 2018年3月7日上午9:43:15
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @return
	 */
	ChannelHandler[] handlers();
}
