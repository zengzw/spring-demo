/*
 * @Project Name: springmvcdemo
 * @File Name: ThreadTest.java
 * @Package Name: com.test.learn.jdk.threadlocal
 * @Date: 2017年11月20日下午5:42:13
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk.threadlocal;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月20日下午5:42:13
 * @see
 */
public class ThreadTest {
	
	public static void main(String[] args) {

		Thread thread = new Thread(
				new Runnable() {
					
					
					@Override
					public void run() {
						
						
					System.out.println(Thread.currentThread().getName() +" : "+Thread.currentThread().isAlive());
					}
				});
		
		thread.start();
		thread.interrupt();
		System.out.println("thread:----"+thread.isInterrupted()); 
		System.out.println("main:----"+Thread.currentThread().isInterrupted());
		System.out.println("main:----"+Thread.currentThread().isInterrupted());
		System.out.println("main:----"+Thread.currentThread().isInterrupted());
		System.out.println("main:----"+Thread.currentThread().isInterrupted());
		System.out.println("main:----"+Thread.currentThread().isInterrupted());
		System.out.println("main:----"+Thread.currentThread().isInterrupted());
	}
}
