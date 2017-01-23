/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread;

/**
 *
 * @author zengzw
 * @date 2017年1月19日
 */
public class ThreadTest extends Thread {



    @Override
    public void run() {

        try {  
            System.out.print(this.getName()+"在等待。。\n");  
            Thread.sleep(2000);                   
        } catch (Exception e) {  
            e.printStackTrace();

        }
        System.out.println("-----"+this.getName()+" 执行完毕");

    }
        public static void main(String[] args) {
            for(int i = 0; i<5; i++){
                ThreadTest run = new ThreadTest();
                run.setName("thread-"+i);
                run.start();
                
            }

        }
    }
