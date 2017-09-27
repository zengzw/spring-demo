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
public class PhaserTest2 {


	public static void main(String[] args) {

		Phaser phaser = new Phaser(5);
		for(int i = 0; i < 5; i++){
			Task task = new Task(phaser);
			Thread thread = new Thread(task);
			thread.start();
		}


		phaser.awaitAdvance(phaser.getPhase());  //countDownLatch.await()

		System.out.println("全部任务执行完了。。。。。");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "执行任务...");

			phaser.arrive();        //countDownLatch.countDown()

		}

	}
}
