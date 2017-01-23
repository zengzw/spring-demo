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
public class Test {
    HttpThreadExecute execute = HttpThreadExecute.getInstance();

    public void test() throws InterruptedException{
        for(int i = 0; i<3; i++){
            System.out.println(">>"+i+"开始执行");
            while(true){
                Thread.sleep(1000);
                if(execute.getFlag()){
                    execute.httpGet();
                    break;
                }else{
                    System.out.println("------");
                }
            }
        }
    }

    public synchronized void test1(){
        Thread thread = Thread.currentThread();
        for(int i = 0; i<3; i++){

            System.out.println(">>"+i+"开始执行");
            synchronized (thread) {
                try {     
                    execute.httpGetKe();
                    thread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(">>"+i+"开始执行完毕");
        }
    }


    public static void main(String[] args){
/*
        Test test = new Test();
        //    test.test();
        test.test1();*/
        
        System.out.println(System.nanoTime());
        System.out.println("--"+System.currentTimeMillis());
    }




}
