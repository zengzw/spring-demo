/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.proctom;


/**
 * 生产者、消费之
 * 生产：一个盘子只能放一个鸡蛋，没消费进行阻塞。等待消费完
 * 消费：没有鸡蛋，等待。等有了再消费
 * @author zengzw
 * @date 2017年1月22日
 */
public class Test6 {

    class Plate{



        public synchronized void getEgg(){
            System.out.println("A---我执行了");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A---继续执行");
        }


        public synchronized void add(Object egg){
            try {
                System.out.println("B-------执行了,休息三秒");
                int i  = 0;
                while(i<3){
                    System.out.println("B--"+i);
                    i++;
                    Thread.sleep(1000);
                }
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    public static void main(String[] args) {
        final Test6 test5 = new Test6();
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
