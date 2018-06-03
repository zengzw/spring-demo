/*
 * @Project Name: springmvcdemo
 * @File Name: ThreadLocalTest.java
 * @Package Name: com.test.learn.jdk.threadlocal
 * @Date: 2017年11月16日上午10:32:26
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
 * @date 2017年11月16日上午10:32:26
 * @see
 */
public class ThreadLocalTest {
	
	final static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
		protected String initialValue() {
			return null;
		};
	};
	
	
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
					threadLocal.set(Thread.currentThread().getName());
				}
			}).start();
		}
	}
}
