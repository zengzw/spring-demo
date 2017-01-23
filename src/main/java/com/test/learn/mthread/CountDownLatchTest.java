/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author zengzw
 * @date 2017年1月19日
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatchTest test = new CountDownLatchTest();
        //倒数计数器
        final CountDownLatch end = new CountDownLatch(10);

        //模拟招募10名枪手到齐后开动大巴，送往“战场”
        for(int i=0;i<10;i++){
            test.execute(end);
            
            System.out.println("----i:"+i+" 执行完了");
        }
     

        //所有枪手到达
        System.out.println("所有枪手到达,开动大巴,送往目的地。。。。");

    }

    public  void execute(final CountDownLatch end) throws InterruptedException{

        Thread.sleep(1000*2);

        //每到达一个枪手，计数器减1
        end.countDown();
        System.out.println("--");
    }

}
