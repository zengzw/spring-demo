/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * @author zengzw
 * @date 2017年7月20日
 */ 
public class AClass {

    public static void main(String[] args) throws InterruptedException{
//        Unsafe unsafe = Unsafe.getUnsafe();
        
      /*  AtomicLong  a = new AtomicLong();
        BlockingQueue linkQueue = new LinkedBlockingQueue();
        BlockingQueue synQueue = new SynchronousQueue();
        BlockingQueue queue = new ArrayBlockingQueue(1);
        queue.put("dd");
        AtomicInteger  b = new AtomicInteger();
       ExecutorService service =  Executors.newFixedThreadPool(20);
//       service.shutdown();
//        ArrayList
//        LinkedList<E>
//       TreeSet<E>
      
//        2147483647
        System.out.println(Integer.MAX_VALUE);
       System.out.println( queue.take());
       
       System.out.println(sun.misc.Hashing.stringHash32((String) "sd"));
       
       System.out.println(Integer.toBinaryString(2));*/
      
       System.out.println(3 >> 3);
       
       
       
       
       
       
       
    }
    
 
    class RunA implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "hello execute";
    }
       
   }
}
