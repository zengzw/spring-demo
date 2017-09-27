/*
 * @Project Name: springmvcdemo
 * @File Name: PhaserDemo.java
 * @Package Name: com.test.learn.concurrent.phaser
 * @Date: 2017年9月25日上午10:46:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.phaser;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月25日上午10:46:49
 * @see
 */
public class PhaserDemo {

	public static void main(String[] args) throws IOException {
		int parties = 4;
		final int phases = 4;
		final Phaser phaser = new Phaser(parties) {
			@Override  
			protected boolean onAdvance(int phase, int registeredParties) {  
				System.out.println("====== Phase : " + phase + " ======registeredParties:"+registeredParties);  
				return registeredParties == 0;  
			}  
		};

		for(int i = 0; i < parties; i++) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {

					for(int phase = 0; phase < phases; phase++) {
						System.out.println(String.format("Thread: %s, phase: %s", Thread.currentThread().getName(), phase));
						phaser.arriveAndAwaitAdvance();
					}
				};

			});
			thread.start();
		}
	}

}
