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
	
	ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		};
	};
	
	
	
	public static void main(String[] args) {
		
	}
}
