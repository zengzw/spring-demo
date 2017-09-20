/*
 * @Project Name: springmvcdemo
 * @File Name: RpcServer.java
 * @Package Name: com.test.learn.rpc.server
 * @Date: 2017年9月1日下午5:31:53
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.mina.filter.codec.RecoverableProtocolDecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.test.learn.rpc.ServiceRegistry;
import com.test.learn.rpc.anotation.RpcService;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月1日下午5:31:53
 * @see
 */
public class RpcServer implements ApplicationContextAware,InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);


	private String serviceAddress;
	private ServiceRegistry serviceRegistry;



	public RpcServer(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}


	public RpcServer(String serviceAddress,ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
		this.serviceAddress = serviceAddress;
	}

	// 存放接口名与服务对象之间的映射关系
	private Map<String, Object> handlerMap = new HashMap<>(); 



	@Override
	public void afterPropertiesSet() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGrop = new NioEventLoopGroup();

		try{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workGrop).channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline()
					.addLast() //TODO 
					.addLast()  //TODO
					.addLast(); //TODO

				}

			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);


			String[] arrayAddress = serviceAddress.split(":");
			String host = arrayAddress[0];
			int port = Integer.parseInt(arrayAddress[1]);

			ChannelFuture future = bootstrap.bind(host, port);


			if(serviceRegistry != null){
				serviceRegistry.register(serviceAddress);
			}

			future.channel().closeFuture().sync();


		}catch(Exception e){
			e.printStackTrace();
		}finally {
			workGrop.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {

		//获取注解RPC serivice 的实例
		Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
		if(MapUtils.isNotEmpty(serviceBeanMap)){
			for(Object beanName : serviceBeanMap.values()){
				String interFaceName = beanName.getClass().getAnnotation(RpcService.class).value().getName();//获取接口名称
				handlerMap.put(interFaceName,beanName);
			}
		}

	}




}
