/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread;



/**
 * 让当前线程停止，给其他线程执行。
 * 
 * @author zengzw
 * @date 2017年1月20日
 */
public class YieldTest {

    
    class ThreadTest extends Thread{
        
        @Override
        public void run() {
            for(int i =0; i<100; i++){
                System.out.println(Thread.currentThread().getName()+">>"+i);
                if(i ==  10 || i== 30 ||i== 50){
                    this.yield();
                    System.out.println("------"+Thread.currentThread().getName()+" yield");
                }
            }
        }
    }
    
    public static void main(String[] a){
        YieldTest yieldTest = new YieldTest();
        ThreadTest test1 =  yieldTest.new ThreadTest();
        test1.setName("001");
        ThreadTest test2 = yieldTest.new ThreadTest();
        test2.setName("002");
        
        test1.start();
        test2.start();
        
        
    }
}
