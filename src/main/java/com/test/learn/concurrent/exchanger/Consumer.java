/*
 * @Project Name: springmvcdemo
 * @File Name: Customer.java
 * @Package Name: com.test.learn.concurrent.exchanger
 * @Date: 2017年9月25日下午2:39:05
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月25日下午2:39:05
 * @see
 */
public class Consumer implements Runnable{

	Exchanger<String>  exchanger;

	Random random = new Random();

	public Consumer(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}



	@Override
	public void run() {
		int i = 0;
		while(true){
			try {
				String result = exchanger.exchange("开吃了"+(i++));
				Thread.sleep(random.nextInt(500));
				
				System.out.println("#####cunsumer result:"+result);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
