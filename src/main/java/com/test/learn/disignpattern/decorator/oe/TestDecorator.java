/*
 * @Project Name: springmvcdemo
 * @File Name: TestDecorator.java
 * @Package Name: com.test.learn.disignpattern.decorator.oe
 * @Date: 2017年11月27日上午10:15:59
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
 * @date 2017年11月27日上午10:15:59
 * @see
 */
public class TestDecorator {
	
	public static void main(String[] args) {

		Greeting greeting = new GreetingAfter(new GreetingBefore(new GreetingImpl()));
		
		greeting.sayHello("Tea");
	}
}
