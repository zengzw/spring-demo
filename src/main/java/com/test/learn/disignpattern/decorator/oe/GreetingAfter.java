/*
 * @Project Name: springmvcdemo
 * @File Name: GreetingAfter.java
 * @Package Name: com.test.learn.disignpattern.decorator.oe
 * @Date: 2017年11月27日上午10:14:58
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
 * @date 2017年11月27日上午10:14:58
 * @see
 */
public class GreetingAfter extends GreetingDecorator{

	public GreetingAfter(Greeting greeting) {
		super(greeting);
	}
	 
	@Override
	public void sayHello(String name) {
		super.sayHello(name);
		after();
	}
	
	public void after(){
		System.out.println("----after----");
	}
}
