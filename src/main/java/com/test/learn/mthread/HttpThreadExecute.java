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
public class HttpThreadExecute {
    //标识方法是否在调用中 true为调用中，false标识调用完毕  
    private static boolean Flag;  


    public static boolean getFlag() {  
        return Flag;  
    }  


    private void setFlag(boolean flag) {  
        Flag = flag;  
    }  


    private HttpThreadExecute()   
    {  
        //初始化标识位  
        setFlag(true);  
    }  

    //注意，这里没有final      
    private static HttpThreadExecute single=null;  

    //静态工厂方法   
    public synchronized  static HttpThreadExecute getInstance() {  
        if (single == null) {    
            single = new HttpThreadExecute();  
        }    
        return single;  
    }  


    //演示方法调用  
    public  synchronized void httpGetKe() {  
        System.out.println("----同步执行完毕");

        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>>"+Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 执行完毕");
                Thread.currentThread().notifyAll();
            }
        }).start();
    }  


    //演示方法调用  
    public void httpGet() {  
        setFlag(false);  
        try {  
            Thread.sleep(200);  
        } catch (Exception e) {  
        }  
        System.out.println("----同步执行完毕");

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 执行完毕");
                setFlag(true);
            }
        }).start();
    }  

}
