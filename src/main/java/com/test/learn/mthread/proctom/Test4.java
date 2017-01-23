/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.proctom;

import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author zengzw
 * @date 2017年1月22日
 */
public class Test4 {

    public static void main(String[] args) {
        Test4 test4 = new Test4();
        final Business business = test4.new Business();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                business.sub();
                
            }
        }).start();
           
        business.main();
    }

    class Business{
        private Lock lock = new ReentrantLock();  
        private Condition condition = lock.newCondition();   
        private boolean bool = true;


        public void main(){
            lock.lock();

            try{
                while(bool){
                    condition.await();
                }

                for(int i = 0; i<10; i++){
                    System.out.println("main for print"+i);
                }
                bool = true;
                condition.signal();

            }catch(Exception e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }


        public void sub(){
            lock.lock();
            try {
                while(!bool){
                    condition.await();
                }

                for(int i = 0; i<10; i++){
                    System.out.println("sub for print:"+i);
                }
                bool = false;
                condition.signal();
            } catch (Exception e) {

                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }
}
