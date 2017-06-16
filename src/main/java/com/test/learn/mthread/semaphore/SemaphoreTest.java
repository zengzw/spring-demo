/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 信号量
 * 
 * 
 * @author zengzw
 * @date 2017年6月7日
 */
public class SemaphoreTest {

    static    ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        
        //只能5个线程访问
        final Semaphore semaphore = new Semaphore(5);
        
        for(int i = 0; i<50; i++){
            final int index = i;
            Runnable runnable = new Runnable() {
                
                @Override
                public void run() {
                    
                  try {
                      //获取许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"get:"+index);
                    
                    Thread.sleep((long)(1000));
                    
                    //范围释放
                    semaphore.release();
                    
                    System.out.println(Thread.currentThread().getName()+"\t-- release avalible size："+semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }  
                }
            };
            
            service.execute(runnable);
        }
        
        service.shutdown();
    }
}
