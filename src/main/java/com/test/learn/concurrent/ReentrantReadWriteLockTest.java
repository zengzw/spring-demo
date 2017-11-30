/*
 * @Project Name: springmvcdemo
 * @File Name: ReentrantReadWriteLockTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2017年11月24日下午4:00:21
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁
 *  读共享，写互斥
 *  
 *  说明：
 *  1，读写、写读、写写 都是互斥的
 *  
 *  2，读读 是共享的。
 *  
 * @author zengzw-1220
 * @date 2017年11月24日下午4:00:21
 * @see
 */
public class ReentrantReadWriteLockTest {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read(){
		try{
			lock.readLock().lock();
//			lock.writeLock().lock();
			
			System.out.println(Thread.currentThread().getName() +"获得锁:"+System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}finally{
			lock.readLock().unlock();
//			lock.writeLock().unlock();
		}

	}


	public static void main(String[] args) {

		final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.read();
			}
		}).start();;
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.read();
			}
		}).start();;
	}
}
