/*
 * @Project Name: springmvcdemo
 * @File Name: PhaserDemo2.java
 * @Package Name: com.test.learn.concurrent.phaser
 * @Date: 2017年9月25日上午11:43:56
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.phaser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Phaser;

/**
 * 当触发条件时，才会执行
 * 
 * 
 * @author zengzw-1220
 * @date 2017年9月25日上午11:43:56
 * @see
 */
public class PhaserDemo2 {
	
	public static void main(String args[]) throws Exception {  
        //  
        final Phaser phaser = new Phaser(1);  
        for(int i = 0; i < 5; i++) {  
            phaser.register();  
            System.out.println("starting thread, id: " + i);  
            final Thread thread = new Thread(new Task(i, phaser));  
            thread.start();  
        }  
          
        //  
        System.out.println("Press ENTER to continue");  
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
        reader.readLine();  
        phaser.arriveAndDeregister();  
    }  
      
    public static class Task implements Runnable {  
        //  
        private final int id;  
        private final Phaser phaser;  
  
        public Task(int id, Phaser phaser) {  
            this.id = id;  
            this.phaser = phaser;  
        }  
          
        @Override  
        public void run() {  
            phaser.arriveAndAwaitAdvance();  
            System.out.println("in Task.run(), phase: " + phaser.getPhase() + ", id: " + this.id);  
        }  
    }  
}
