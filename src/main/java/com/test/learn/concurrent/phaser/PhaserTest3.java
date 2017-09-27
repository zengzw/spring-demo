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
 * CountDownLatch
 * @author zengzw-1220
 * @date 2017年9月23日下午2:36:15
 * @see
 */
public class PhaserTest3 {


	public static void main(String[] args) {

		Phaser phaser = new Phaser(3){
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("执行onAdvance方法.....;phase:" + phase + "registeredParties=" + registeredParties);
				return phase == 3; 
			}
		};


		for(int i = 0; i < 3; i++){
			Task task = new Task(phaser);
			Thread thread = new Thread(task);
			thread.start();
		}

		while(!phaser.isTerminated()){
			phaser.arriveAndAwaitAdvance();// 主线程一直等待
		}


		System.out.println("全部任务执行完了。。。。。");
	}


	static class Task implements Runnable{

		final Phaser phaser;
		public Task(Phaser phaser){
			this.phaser = phaser;
		}

		@Override
		public void run() {
			do{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "执行任务...");

				phaser.arriveAndAwaitAdvance();
				
			}while(!phaser.isTerminated());
			

		}

	}
}
