/*
 * @Project Name: springmvcdemo
 * @File Name: TestExchanger.java
 * @Package Name: com.test.learn.concurrent.exchanger
 * @Date: 2017年9月25日下午2:35:11
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.exchanger;

import java.util.concurrent.Exchanger;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月25日下午2:35:11
 * @see
 */
public class TestExchanger {
	
	public static void main(String[] args) {

		Exchanger<String> exchanger = new Exchanger<>();
		
		new Thread(new Consumer(exchanger)).start();
		new Thread(new ProduceEx(exchanger)).start();
		
	}
}
