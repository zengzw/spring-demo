/*
 * @Project Name: springmvcdemo
 * @File Name: SemaphoreTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月17日上午9:49:23
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * 
 *  Semaphore分为单值和多值两种，前者只能被一个线程获得，后者可以被若干个线程获得。
 *  
 *  在这个停车场系统中，车位是公共资源，每辆车好比一个线程，看门人起的就是信号量的作用。
 * @author zengzw-1220
 * @date 2018年5月17日上午9:49:23
 * @see
 */
public class SemaphoreTest {
	
	
	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();
		
		final Semaphore semaphore = new Semaphore(5);
		
		for(int i = 0; i< 10; i++){
			final int no  = i;
			service.submit(
				new Runnable() {

					@Override
					public void run() {
						try {
							semaphore.acquire();//获取许可
							
							System.out.println("accessing："+no);
							Thread.sleep((long) (Math.random() * 2000));
							
							semaphore.release(); //用完，释放资源
							  //availablePermits()指的是当前信号灯库中有多少个可以被使用
	                        System.out.println("-----------------" + semaphore.availablePermits()); 
						} catch (InterruptedException e) {
							e.printStackTrace();
						} 
					}
				}
			);
		}
		
		service.shutdown();
	}
	
	
}
