/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONAwareSerializer;
import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2017年7月14日
 */
public class UserCallable implements Callable<User>{

    @Override
    public User call() throws Exception {
      User user = new User();
      user.setId(11);
      user.setName("ame");
      return user;
    }
    
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
       ExecutorService executorService = Executors.newCachedThreadPool();
       
       Future<User> future = executorService.submit(new UserCallable());
       
       System.out.println("----submit ----- ");
       
       
       System.out.println(future.isDone());
       
       System.out.println(future.get());
    }

}
