/*
 * @Project Name: springmvcdemo
 * @File Name: HystrixTest.java
 * @Package Name: com.test.learn.hystrix
 * @Date: 2017年11月29日下午2:48:25
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.hystrix;

import java.util.concurrent.Future;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月29日下午2:48:25
 * @see
 */
public class HystrixTest {

	public static void main(String[] args) {

		for(int i = 0; i<10; i++){
			new Thread(new Runnable() {
				
				
				@Override
				public void run() {
					GetStockServiceCommoand commoand = new GetStockServiceCommoand();
					String result = commoand.execute();
					//同步调用
					System.out.println(result);
				}
			}).start();
		
		}




		//异步调用
		//Future<String> future = commoand.queue();

		//响应式,配合RxJava
	}
}
