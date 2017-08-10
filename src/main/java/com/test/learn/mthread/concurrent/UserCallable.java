/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.concurrent;

import java.util.concurrent.Callable;

import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2017年7月14日
 */
public class UserCallable implements Callable<User>{

    @Override
    public User call() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("--------runable------");

        User user = new User();
        user.setId(22);
        return user;

    }





}
