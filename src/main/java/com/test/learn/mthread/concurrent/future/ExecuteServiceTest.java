/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.test.learn.mthread.concurrent.UserCallable;
import com.test.learn.mthread.concurrent.UserRunnable;
import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2017年7月14日
 */
public class ExecuteServiceTest {

    public static void main(String[] args) throws Exception {
        //        test2();
        test3();
    }


    public static void test1() throws Exception{
        Callable<String> str = Executors.callable(new UserRunnable(), "result");
        System.out.println("run end:"+str.call());
    }

    public static void test2() throws Exception{
        ExecutorService executorService  = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new UserRunnable()," return result");
        // future.cancel(true);
        System.out.println(future.isDone());

        String t =  future.get();
        System.out.println("get future result:" + t);


    }
    
    
    
    public static void test3() throws Exception{
        ExecutorService executorService  = Executors.newCachedThreadPool();


        FutureTask<User> task = new FutureTask<>( new UserCallable());
        executorService.submit(task);
        System.out.println(task.isDone());

        User t =  task.get();
        System.out.println("get future result:" + t);


    }
}
