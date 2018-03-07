/*
 * @Project Name: springmvcdemo
 * @File Name: ConnectionWatchdog.java
 * @Package Name: com.test.learn.netty.upgrade.heartbeat.client
 * @Date: 2018年3月7日上午10:20:08
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.upgrade.heartbeat.client;


import java.util.concurrent.TimeUnit;

import com.test.learn.netty.upgrade.heartbeat.ChannelHandlerHolder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

/**
 ** 重连检测狗，当发现当前的链路不稳定关闭之后，进行12次重连 
 *
 * @author zengzw-1220
 * @date 2018年3月7日上午10:20:08
 * @see
 */
@Sharable
public abstract class ConnectionWatchdog extends ChannelInboundHandlerAdapter implements TimerTask,ChannelHandlerHolder{


	private final Bootstrap bootstrap;

	private final Timer timer;


	private final int port;

	private final String host;

	private volatile boolean reconnect = true;

	private int attempts;

	public ConnectionWatchdog(Bootstrap bootstrap,Timer timer,int port,String host){
		this.bootstrap = bootstrap;
		this.timer = timer;
		this.port = port;
		this.host = host;
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("当前链路已经激活了，重连尝试次数重新置为0");  

		attempts = 0;
		ctx.fireChannelActive();
	}	


	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("链接关闭");
		if(reconnect){
			System.out.println("链接关闭，将进行重新链接:"+attempts);
			if(attempts < 12){
				attempts ++;

				int timeout = 2 << attempts;
				timer.newTimeout(this, timeout, TimeUnit.MILLISECONDS);
			}
		}

		ctx.fireChannelInactive();
	}



	@Override
	public void run(Timeout timeout) throws Exception {
		ChannelFuture future ;

		synchronized (bootstrap) {
			bootstrap.handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(handlers());
				}
			});

			future = bootstrap.connect(host, port);
		}


		future.addListener(new ChannelFutureListener(){

			@Override
			public void operationComplete(ChannelFuture future) throws Exception {

				boolean isSuccess = future.isSuccess();
				///如果重连失败，则调用ChannelInactive方法，再次出发重连事件，一直尝试12次，如果失败则不再重连
				if(!isSuccess){
					System.out.println("--重新链接失败......");
					future.channel().pipeline().fireChannelInactive();
				}else{
					System.out.println("--重新链接成功......");
				}
			}

		});


	}
}
