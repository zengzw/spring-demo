/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author zengzw
 * @date 2017年8月3日
 */
public class OtherTest {
    
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    
    public static void main(String[] args) throws InterruptedException{
    
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                threadLocal.set("kktalk-thread0g");
                
            }
        },"thread-001").start();
        
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                threadLocal.set("kktalk-thread");
                System.out.println(threadLocal.get());
            }
        },"thread-002").start();
        
        
        
        
        //      Unsafe unsafe = Unsafe.getUnsafe();
        
        
      /*  Lock lock = new ReentrantLock();

        OtherTest test = new OtherTest();
        AtomicLong  a = new AtomicLong();
        BlockingQueue linkQueue = new LinkedBlockingQueue();
        BlockingQueue synQueue = new SynchronousQueue();
        BlockingQueue priQueue = new PriorityBlockingQueue();
        
        BlockingQueue queue = new ArrayBlockingQueue(1);
        for(int i = 0; i<5; i++){
        queue.add("s");
            System.err.println(queue.size());
        }
        
        
        
        AtomicInteger  b = new AtomicInteger();
        ExecutorService service =  Executors.newFixedThreadPool(20);
        ExecutorService cacheService =  Executors.newCachedThreadPool();
        service.submit(test.new RunA());
        service.shutdown();
        //      ArrayList
        //      LinkedList<E>
        //     TreeSet<E>
//      HashMap<K, V>
//        Hashtable<K, V>
//        Collections.synchronizedList(list)
//        ConcurrentHashMap<K, V>
//        Collections.synchronizedMap(m)
        
        
        //      2147483647
        System.out.println(Integer.MAX_VALUE);
        System.out.println( queue.take());

        System.out.println(sun.misc.Hashing.stringHash32((String) "sd"));*/
        
        

    }


    class RunA implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "hello execute";
        }

    }
}
