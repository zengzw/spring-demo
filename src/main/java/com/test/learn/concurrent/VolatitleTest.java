/*
 * @Project Name: springmvcdemo
 * @File Name: VolatitleTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年4月16日下午3:00:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月16日下午3:00:50
 * @see
 */
public class VolatitleTest {
	
	private volatile int count;
	
	public void add(){
		System.out.println("add:"+ (++count));
	}
	
	public void des(){
		System.out.println("sub:"+ (--count));
	}
	
	
	public static void main(String[] args) {

		VolatitleTest volatitleTest = new VolatitleTest();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i<10; i++){
					
					volatitleTest.add();
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i<10; i++){
					
					volatitleTest.des();
				}
			}
		}).start();
	}
}
