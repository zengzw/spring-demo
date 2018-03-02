/*
 * @Project Name: springmvcdemo
 * @File Name: FixedThreadPollTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年2月27日下午3:17:30
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月27日下午3:17:30
 * @see
 */
public class FixedThreadPollTest {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = null;
		// 创建一个可重用固定线程数的线程池  
		ExecutorService pool = Executors.newFixedThreadPool(2);  
		// 创建线程  
		Thread t1 = new MyThread();  
		Thread t2 = new MyThread();  
		Thread t3 = new MyThread();  
		Thread t4 = new MyThread();  
		Thread t5 = new MyThread();  
		// 将线程放入池中进行执行  
		pool.execute(t1);  
		pool.execute(t2);  
		pool.execute(t3);  
		pool.execute(t4);  
		pool.execute(t5);  
		// 关闭线程池  
		pool.shutdown();  

	}


}


class MyThread extends Thread {  
		@Override  
		public void run() {  
			System.out.println(Thread.currentThread().getName() + "正在执行。。。");  
		}  
	}
