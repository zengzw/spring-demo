/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.proctom;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author zengzw
 * @date 2017年1月22日
 */
public class Test3 {

    private Lock lock = new ReentrantLock();

    private Condition userKey = lock.newCondition();


    public static void main(String[] args) {
        Test3 test = new Test3();

        Produce produce = test.new Produce();
        Customer customer = test.new Customer();

        produce.start();
        customer.start();
    }


    class Customer extends Thread{

        @Override
        public void run() {
            execute();
        }

        private void execute(){
            lock.lock();
            System.out.println("---customer 拿到锁了。用3秒后归还");
            int i = 0;
            try {
                while(i<3){
                    System.out.println("\t---cusotmer 休息:"+i);
                    this.sleep(1000);
                    i++;
                }
                
                userKey.signal();
                System.out.println("---customer 休息够了，叫其他人使用！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
                System.out.println("--customer 释放lock");
            }
         

        }
    }




    class Produce extends Thread{
        @Override
        public void run() {
            execute();
        }

        private void execute(){
            lock.lock();
            System.out.println("#####produce,获得锁，我要等下再用！");
            try {
                userKey.await();
                
                System.out.println("####produce 再次到了锁，继续使用！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
                System.out.println("----produce 释放lock");
            }

        }
    }
}
