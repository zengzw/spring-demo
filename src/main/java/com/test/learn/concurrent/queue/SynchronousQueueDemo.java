/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.concurrent.queue;

import java.util.concurrent.SynchronousQueue;

/**
 *  Java 6的并发编程包中的SynchronousQueue是一个没有数据缓冲的BlockingQueue，生产者线程对其的插入操作put必须等待消费者的移除操作take，反过来也一样。
 *  
 * @author zengzw
 * @date 2017年8月3日
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<5; i++){
                    System.out.println("==put thread start"+i);
                    try {
                        queue.put(1);
                        System.out.println("==put size:"+queue.size());
                    } catch (InterruptedException e) {
                    }
                    System.out.println("==put thread end:"+i);
                }
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-take thread start");
                try {
                    System.out.println("-take from putThread: " + queue.take());
                } catch (InterruptedException e) {
                }
                System.out.println("-take thread end");
            }
        });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();
    }
}
