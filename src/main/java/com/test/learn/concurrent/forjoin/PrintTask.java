/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.concurrent.forjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zengzw
 * @date 2016年11月21日
 */
public class PrintTask extends RecursiveAction{

    /**
     * 最多打印10个。
     */
    private static final int MAX = 5;

    private int start;
    private int end;

    public PrintTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if((end-start)<MAX){
            for(int i = start; i<end; i++){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }else{
            int middle = (start + end)/2; //将任务分解
            PrintTask task1 = new PrintTask(start, middle);
            PrintTask task2 = new PrintTask(middle, end);

            //并行执行两个任务
            task1.fork();
            task2.fork();

        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(0, 100));
        
        pool.awaitTermination(2, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束  
        // 关闭线程池  
        pool.shutdown();  
    }

}
