/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread;



/**
 * 线程合并
 * 
 * 线程合并是优先执行调用该方法的线程，再执行当前线程
 * 
 * @author zengzw
 * @date 2017年1月20日
 */
public class JoinTest {

    
    class ThreadTest extends Thread{
        
        @Override
        public void run() {
            for(int i =0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+">>"+i);
            }
        }
    }
    
    public static void main(String[] a){
        JoinTest yieldTest = new JoinTest();
        ThreadTest test1 =  yieldTest.new ThreadTest();
        test1.setName("001");
        ThreadTest test2 = yieldTest.new ThreadTest();
        test2.setName("002");
        
        test1.start();
        test2.start();
        
        try {
            test1.join();
            test2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        System.out.println("主线程");
        
        
        
    }
}
