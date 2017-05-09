/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.spring.proxy;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 *
 * @author zengzw
 * @date 2017年2月27日
 */
public class TicketBeforeAdvice implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("beforeAdivce:----welcome");
    }

}
