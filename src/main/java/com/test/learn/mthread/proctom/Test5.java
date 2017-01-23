/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.proctom;

import java.util.ArrayList;
import java.util.List;


/**
 * 生产者、消费之
 * 生产：一个盘子只能放一个鸡蛋，没消费进行阻塞。等待消费完
 * 消费：没有鸡蛋，等待。等有了再消费
 * @author zengzw
 * @date 2017年1月22日
 */
public class Test5 {

    class Plate{
        List<Object> list = new ArrayList<Object>();


        public synchronized Object getEgg(){
            //如果等于0，等待
            while(list.size() == 0){
                try {
                    wait(); //阻塞当前线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            Object object = list.get(0);
            list.clear();

            notify(); //唤醒其他线程

            System.out.println("拿到鸡蛋了");
            return object;
        }


        public synchronized void add(Object egg){
            while(list.size() > 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.add(egg);
            System.out.println("添加了一个鸡蛋");
            notify(); //通知其他线程消费。

        }
    }



    public static void main(String[] args) {
        final Test5 test5 = new Test5();
        final Plate plate = test5.new Plate();

        for(int i = 0; i<10;i++){
         

            new Thread(new Runnable() {

                @Override
                public void run() {
                    plate.getEgg();
                }
            }).start();
            
            
            new Thread(new Runnable() {

                @Override
                public void run() {

                    Plate egg = test5.new Plate();
                    plate.add(egg);

                }
            }).start();
        }
    }
}
