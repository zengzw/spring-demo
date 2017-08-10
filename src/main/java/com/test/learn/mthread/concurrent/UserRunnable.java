/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.concurrent;

/**
 *
 * @author zengzw
 * @date 2017年7月14日
 */
public class UserRunnable implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       System.out.println("--------runable------");
        
    }
    
    
    
    

}
