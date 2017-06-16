/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

/**
 *
 * @author zengzw
 * @date 2017年5月2日
 */
public class Htmlunit {
 
    public volatile int inc = 0;
    
    public void increase() {
        inc++;
    }
 
    public static void main(String[] args) {
        final Htmlunit test = new Htmlunit();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
 
        System.out.println(Thread.activeCount());
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        
        
        System.out.println(test.inc);
    }
}
