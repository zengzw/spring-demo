/*
 * @Project Name: springmvcdemo
 * @File Name: LockSupportTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月11日下午2:58:57
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月11日下午2:58:57
 * @see
 */
public class LockSupportTest {
	
	public static void main(String[] args) {
		System.out.println("start");
		
		LockSupport.parkNanos(1000000000);
		
		System.out.println("end");
		
		//一开始会block线程，直到给定时间过去后才往下走

		System.out.println("----------------");
		
		
		System.out.println("start1");
		LockSupport.unpark(Thread.currentThread());
		LockSupport.parkNanos(1000000000);
		System.out.println("end1");
		//不会block，因为一开始给了一个permit

		System.out.println("-------------------");
		
		System.out.println("start2");
		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());
		LockSupport.parkNanos(1000000000);
		System.out.println("inter2");
		LockSupport.parkNanos(1000000000);
		System.out.println("end2");
		//第一个park不会block，第2个会，因为permit不会因为多次调用unpark就积累
		
	}
}
