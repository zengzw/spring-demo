/*
 * @Project Name: springmvcdemo
 * @File Name: PhaserTest1.java
 * @Package Name: com.test.learn.concurrent.phaser
 * @Date: 2017年9月23日下午2:36:15
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.phaser;

import java.util.concurrent.Phaser;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月23日下午2:36:15
 * @see
 */
public class PhaserTest1 {
	
	
	public static void main(String[] args) {

		Phaser phaser = new Phaser(5);
		for(int i = 0; i < 5; i++){
			Task task = new Task(phaser);
			Thread thread = new Thread(task);
			thread.start();
		}
	}
		
	
static class Task implements Runnable{

	final Phaser phaser;
	public Task(Phaser phaser){
		this.phaser = phaser;
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() +"：执行完任务.等待其他任务执行....");
		
		//等待其他线程执行完任务。（CyclicBarrier 屏障点类似)
		phaser.arriveAndAwaitAdvance();
		
		
		System.out.println(Thread.currentThread().getName() +":继续执行任务.....");
		
	}
	
}
}
