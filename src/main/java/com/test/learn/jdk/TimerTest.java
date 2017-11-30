/*
 * @Project Name: springmvcdemo
 * @File Name: TimerTest.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2017年11月24日下午4:08:18
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.storm.shade.org.apache.curator.TimeTrace;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月24日下午4:08:18
 * @see
 */
public class TimerTest extends TimerTask{

	@Override
	public void run() {
		System.out.println("任务执行。。。");
	}
	
	public static void main(String[] args) {

	/*	TimerTest test = new TimerTest();
		Timer timer = new Timer();
		timer.schedule(test,1);*/         
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){System.out.println("--");}
			}
		});
		
		thread.setDaemon(true);
		thread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
