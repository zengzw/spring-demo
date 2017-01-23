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
public class ThreadRun extends Thread {

    HttpThreadExecute s2;  
    public  ThreadRun() {  
        s2= HttpThreadExecute.getInstance();   
    }  

    @Override
    public void run() {
        while(true){
            if (s2.getFlag()) {                
                s2.httpGet(); 
                break;//执行完毕退出循环  
            }else {  
                try {  
                    System.out.print(this.getName()+"在等待。。\n");  
                    Thread.sleep(1000);                   
                } catch (Exception e) {  
                    e.printStackTrace();
                }  
            }  
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i<5; i++){
            ThreadRun run = new ThreadRun();
            run.setName("thread-"+i);
            run.start();
        }

    }
}
