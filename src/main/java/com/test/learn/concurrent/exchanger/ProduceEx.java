/*
 * @Project Name: springmvcdemo
 * @File Name: AEx.java
 * @Package Name: com.test.learn.concurrent.exchanger
 * @Date: 2017年9月25日下午2:35:43
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
 * @date 2017年9月25日下午2:35:43
 * @see
 */
public class ProduceEx implements Runnable{

	Exchanger<String> exchanger;
	Random random = new Random();

	public ProduceEx(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}



	@Override
	public void run() {
		int i = 0;
		while(true){
			try {
				Thread.sleep(random.nextInt(500));
				String result = exchanger.exchange("生产了"+(i++));
				
				System.out.println("---Produce result:"+result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
