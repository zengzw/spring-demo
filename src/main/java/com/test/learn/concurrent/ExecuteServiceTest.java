/*
 * @Project Name: springmvcdemo
 * @File Name: ExecuteServiceTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年6月8日下午5:46:54
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
 * @date 2018年6月8日下午5:46:54
 * @see
 */
public class ExecuteServiceTest {
	
	 public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Future<String> future =  executorService.submit(new Callable(){

			@Override
			public Object call() throws Exception {

				return "call result";
			}
			
		});
		
		System.out.println(future.get());
	}
}
