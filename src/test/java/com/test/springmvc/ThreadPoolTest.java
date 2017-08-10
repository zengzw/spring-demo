/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zengzw
 * @date 2017年8月3日
 */
public class ThreadPoolTest implements Runnable{
    public void run() {   
        synchronized(this) {   
          try{  
              System.out.println(Thread.currentThread().getName());  
              Thread.sleep(1000);  
          }catch (InterruptedException e){  
              e.printStackTrace();  
          }  
        }   
   }   
     
   public static void main(String[] args) {   
//       BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();  
       BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(5);  
       
       ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);  
       for (int i = 0; i < 11; i++) {     
           executor.execute(new Thread(new ThreadPoolTest(), "TestThread".concat(""+i)));     
           int threadSize = queue.size();  
           System.out.println("线程队列大小为-->"+threadSize);  
       }     
       executor.shutdown();    
   }  
}
