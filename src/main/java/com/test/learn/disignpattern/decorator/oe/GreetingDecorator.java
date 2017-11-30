/*
 * @Project Name: springmvcdemo
 * @File Name: GreetingDecorator.java
 * @Package Name: com.test.learn.disignpattern.decorator.oe
 * @Date: 2017年11月27日上午10:12:24
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
 * @date 2017年11月27日上午10:12:24
 * @see
 */
public abstract class GreetingDecorator implements Greeting{

	private Greeting greeting;
	
	public GreetingDecorator(Greeting greeting){
		this.greeting = greeting;
	}
	
	@Override
	public void sayHello(String name) {
	
		greeting.sayHello(name);
	}
}
