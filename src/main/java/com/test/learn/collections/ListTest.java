/*
 * @Project Name: springmvcdemo
 * @File Name: ListTest.java
 * @Package Name: com.test.learn.collections
 * @Date: 2018年3月16日下午5:44:56
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.collections;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月16日下午5:44:56
 * @see
 */
public class ListTest {
	 static ExecutorService executorService  = Executors.newCachedThreadPool();
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		System.out.println("====start=========");
		
		int result = executeTask();
		
		System.out.println("========end========="+result);
	}
	
	public static int executeTask() throws InterruptedException, ExecutionException{
		FutureTask<Integer> task = new FutureTask(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("===task");
				Thread.sleep(3000);
				return 10;
			}
		});
		executorService.submit(task);
		
		return task.get();
	}
}
