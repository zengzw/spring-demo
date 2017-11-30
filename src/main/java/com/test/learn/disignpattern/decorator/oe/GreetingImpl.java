/*
 * @Project Name: springmvcdemo
 * @File Name: GreetingImpl.java
 * @Package Name: com.test.learn.disignpattern.decorator.oe
 * @Date: 2017年11月27日上午10:11:55
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disignpattern.decorator.oe;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月27日上午10:11:55
 * @see
 */
public class GreetingImpl implements Greeting{

	public GreetingImpl() {
	};
	@Override
	public void sayHello(String name) {

		System.out.println("Hello! " + name);	
		
	}
}
