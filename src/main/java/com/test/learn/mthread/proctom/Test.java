/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.proctom;

/**
 *
 * @author zengzw
 * @date 2017年1月20日
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("a");
        final Thread thread = Thread.currentThread();
        synchronized (thread) {
           
            thread.wait();
            
            thread.sleep(2000);
            thread.notify();
        }

       
        System.out.println("b");
    }
}
