/*
 * @Project Name: springmvcdemo
 * @File Name: RunnableCallableFutureFutureTask.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年2月28日下午3:45:17
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月28日下午3:45:17
 * @see
 */
public class RunnableCallableFutureFutureTask {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService service = Executors.newCachedThreadPool();
		
		/*
		 * runnable 没有返回者
		 */
		Future<String> futureResult = (Future<String>) service.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable->run");
			}
		});
		
		System.out.println(futureResult.get());
	//	service.shutdown();
		
		
		/**
		 * Callable
		 * 返回者
		 */
		Future<String> future = service.submit(new Callable<String>(){
			@Override
			public String call() throws Exception {
				System.out.println("callable->run...");
				Thread.sleep(2000);
				return "result";
			}
			
		});
		System.out.println("isDone:"+future.isDone() +" cancelled:" + future.isCancelled());
//		System.out.println(future.cancel(true));
		
		System.out.println("callable result:"+future.get());
		System.out.println("---------over----------");
		service.shutdown();
	}
}
