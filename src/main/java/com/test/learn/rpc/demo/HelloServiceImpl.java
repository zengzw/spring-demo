/*
 * @Project Name: springmvcdemo
 * @File Name: HelloServiceImpl.java
 * @Package Name: com.test.learn.rpc.demo
 * @Date: 2017年9月26日下午5:25:35
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.demo;

import com.test.learn.rpc.anotation.RpcService;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月26日下午5:25:35
 * @see
 */
@RpcService(HelloService.class) 
public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String name) {

		return "hello name";
	}
}
