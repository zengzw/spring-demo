/*
 * @Project Name: springmvcdemo
 * @File Name: FutureTaskTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月17日下午3:05:27
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月17日下午3:05:27
 * @see
 */
public class FutureTaskTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		FutureTask<String> futureTask = new FutureTask<String>(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("--run future task");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}, "task result");
		
		
		new Thread(futureTask).start();
		
		
		while(!futureTask.isDone()){
			System.out.println("---");
			System.out.println(futureTask.get());
		}
		
	}
}
