/*
 * @Project Name: springmvcdemo
 * @File Name: TestRpcServer.java
 * @Package Name: com.test.springmvc
 * @Date: 2017年9月27日上午10:38:33
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.learn.rpc.RpcProxy;
import com.test.learn.rpc.demo.HelloService;
import com.test.springmvc.TestWithSpring;

/**
 * 
 * 1： 把applicationContenxt xml 配置放开	<!-- <import resource="rpc-client-config.xml"/>  -->
 *  		 注释	<!-- <import resource="rpc-server-config.xml"/>  -->
 * 
 * 
 * 
 * @author zengzw-1220
 * @date 2017年9月27日上午10:38:33
 * @see
 */
public class TestRpcClient extends TestWithSpring{
	


	@Autowired
	RpcProxy rpcProxy;
	
	@Test
	public void testServcie(){
		  HelloService helloService = rpcProxy.create(HelloService.class);
		  String result = helloService.hello("phoenix");
		  System.out.println("----result:"+result);
	}
	
}
