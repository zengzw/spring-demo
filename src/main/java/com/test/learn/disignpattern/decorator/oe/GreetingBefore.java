/*
 * @Project Name: springmvcdemo
 * @File Name: GreetingBefore.java
 * @Package Name: com.test.learn.disignpattern.decorator.oe
 * @Date: 2017年11月27日上午10:13:54
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
 * @date 2017年11月27日上午10:13:54
 * @see
 */
public class GreetingBefore extends GreetingDecorator{

	public GreetingBefore(Greeting greeting) {
		super(greeting);
	}
	
	@Override
	public void sayHello(String name) {

		before();
		
		super.sayHello(name);
	}
	
	public void before(){
		System.out.println("----before----");
	}
}
