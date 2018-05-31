/*
 * @Project Name: springmvcdemo
 * @File Name: ReentrantLockTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月28日上午11:49:34
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月28日上午11:49:34
 * @see
 */
public class ReentrantLockTest {
	
	private ReentrantLock tLock = new ReentrantLock();
	
	public void getNum(int i){
		try {
			
			tLock.lock();
			System.out.println("get num:"+i);
		} finally {
			tLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ReentrantLockTest test = new ReentrantLockTest();
		for(int i = 0; i< 100; i++){
			final  int idx = i;
			new Thread(()->{
				test.getNum(idx);
			}).start();
		}
		
	}
}
