/*
 * @Project Name: springmvcdemo
 * @File Name: BooleanMutexTest.java
 * @Package Name: com.test.learn.concurrent.aqs
 * @Date: 2018年5月28日上午11:13:48
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月28日上午11:13:48
 * @see
 */
public class BooleanMutexTest {

	public static void main(String [] args) {
		/*	// 测试1 初始化为true，不会阻塞，会唤醒被状态false阻塞的线程
		BooleanMutex mutex = new BooleanMutex();
		mutex.set(true);
		try {
			System.out.println("1. =======>" + System.currentTimeMillis());
			mutex.lock(); //不会被阻塞
			System.out.println("1. =======>" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		// 测试2 初始化false，主线程阻塞，子线程为true，唤醒
		/*final BooleanMutex mutex2 = new BooleanMutex();
		try {
			ExecutorService executor = Executors.newCachedThreadPool();
			System.out.println("2.1 =======>" + System.currentTimeMillis());
			executor.submit(new Callable() {
				public Object call() throws Exception {
					Thread.sleep(1000);
					mutex2.set(true);
					System.out.println("2.2 =======>" + System.currentTimeMillis());
					return null;
				}
			});

			mutex2.lock(); //会被阻塞，等异步线程释放锁对象
			System.out.println("2.3 =======>" + System.currentTimeMillis());

			System.out.println("2.4 =======>" + System.currentTimeMillis());
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		// 测试3 初始化为true，不会被阻塞
		try {
			final BooleanMutex mutex3 = new BooleanMutex();
			mutex3.set(true);
			System.out.println("3.1 =======>" + System.currentTimeMillis());
			final CountDownLatch count = new CountDownLatch(10);
			ExecutorService executor = Executors.newCachedThreadPool();

			for (int i = 0; i < 10; i++) {
				executor.submit(new Callable() {
					public Object call() throws Exception {
						mutex3.lock();
						System.out.println("3.2 =======>" + System.currentTimeMillis());
						count.countDown();
						return null;
					}
				});
			}
			count.await();
			System.out.println("3.3 =======>" + System.currentTimeMillis());
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 测试4 初始化false，子线程阻塞
		try {
			final BooleanMutex mutex4 = new BooleanMutex();//初始为false
			final CountDownLatch count = new CountDownLatch(10);
			ExecutorService executor = Executors.newCachedThreadPool();
			System.out.println("4.1 =======>" + System.currentTimeMillis());
			for (int i = 0; i < 10; i++) {
				executor.submit(new Callable() {
					public Object call() throws Exception {
						mutex4.lock();//被阻塞
						System.out.println("4.2 =======>" + System.currentTimeMillis());
						count.countDown();
						return null;
					}
				});
			}
			Thread.sleep(1000);
			mutex4.set(true);
			System.out.println("4.3 =======>" + System.currentTimeMillis());
			count.await();
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
