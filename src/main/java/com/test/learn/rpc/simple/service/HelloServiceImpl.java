/*
 * @Project Name: springmvcdemo
 * @File Name: HelloServiceImpl.java
 * @Package Name: com.test.learn.rpc.simple
 * @Date: 2018年3月12日上午10:14:04
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.simple.service;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月12日上午10:14:04
 * @see
 */
public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String name) {

		return "hello "+name;
	}
}
