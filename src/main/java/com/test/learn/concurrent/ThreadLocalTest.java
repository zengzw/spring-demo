/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.concurrent;

/**
 *
 * @author zengzw
 * @date 2017年2月10日
 */
public class ThreadLocalTest {

    ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    
    
    public void setValue(String value){
        threadLocal.set(value);
    }
    
    
    public String getValue(){
        return threadLocal.get();
    }
    
    
    public static void main(String[] args) {
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                for(int i = 0; i<3; i++){
                    ThreadLocalTest testThreadLocal = new ThreadLocalTest();
                    testThreadLocal.setValue("value:"+i);
                    
                    System.out.println(testThreadLocal.getValue());
                    
                    System.out.println("--------------");
                }
                
            }
        }).start();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                for(int i = 10; i<13; i++){
                    ThreadLocalTest testThreadLocal = new ThreadLocalTest();
                    testThreadLocal.setValue("value1:"+i);
                    
                    System.out.println(testThreadLocal.getValue());
                    
                    System.out.println("--------------");
                }
                
            }
        }).start();
        
      
    }
    
}
