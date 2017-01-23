/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.proctom;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.storm.generated.DistributedRPC.AsyncProcessor.execute;

/**
 *
 * @author zengzw
 * @date 2017年1月22日
 */
public class Test2 {

    private PriorityQueue<Integer>  queue  = new PriorityQueue<Integer>();

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();


    public static void main(String[] args) {
        Test2 test = new Test2();
        
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

            while(true){
                lock.lock();
                try{
                    while(queue.size() == 0){
                        System.out.println("队列为空,等待数据");
                        notEmpty.await();
                    }

                    System.out.println("customer:"+queue.poll());

                    notFull.signal(); //equals object notify

                    System.out.println("customer take one."+queue.size());

                }catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    lock.unlock();
                }
            }

        }
    }




    class Produce extends Thread{
        @Override
        public void run() {
            execute();
        }

        private void execute(){
            while(true){
                lock.lock();

                try{
                    while(queue.size() == 10){
                        System.out.println("-----队列已满");
                        notFull.await();
                    }

                    queue.offer(1);
                    notEmpty.signal();
                    System.out.println("向队列插入一个元素."+queue.size());

                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    lock.unlock();
                }

            }
        }
    }
}
