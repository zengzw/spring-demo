/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2014年10月14日
 */
public class FutureTest {


    public static void main(String[] args) throws Exception {
        FutureTest future = new FutureTest();
        // future.test();

        ExecutorService executorService   = Executors.newCachedThreadPool();;
        int count = 10;
        List<User> list = new ArrayList<User>();
        for(int i = 0; i< count; i++){
           executorService.submit(future.testUser("kktalk",list));
        }

        executorService.shutdown();
      
    }

    class MyCallable implements Callable<User>{
        private String name;
        public MyCallable(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public User call() throws Exception {

            User user  = new User();
            user.setName(getName());

            return user;
        }

    }

    public ListenableFutureTask<User> testUser(String name,final List<User> result){

        ListenableFutureTask<User> task = new ListenableFutureTask<User>(new MyCallable(name));
        task.addCallback(new ListenableFutureCallback<User>() {

            @Override
            public void onFailure(Throwable arg0) {
                System.out.println("--"+arg0.getMessage());

            }

            @Override
            public void onSuccess(User user) {
                System.out.println(Thread.currentThread().getName()+" user-name:"+user.getName());
            }
        });
        
        return task;
    }

    @SuppressWarnings("unchecked")
    public  void test() throws Exception {

        ListenableFutureTask<String> task = new ListenableFutureTask<String>(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2 * 1000L);

                System.out.println("=======task execute");
                int i = 1 / 0;
                return "hello";
            }
        });

        task.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("===success callback 1");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("------:"+t.getMessage());
            }
        });


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(task);
        String result = task.get();
        System.out.println(result);

    }


}
