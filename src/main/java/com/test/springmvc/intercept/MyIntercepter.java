/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.intercept;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 流量控制
 * 
 * @author zengzw
 * @date 2016年5月9日
 */
public class MyIntercepter implements MethodInterceptor{


    RateLimiter rateLimiter = RateLimiter.create(10);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("arguments:----------"+invocation.getArguments());
        System.out.println("method:----------"+invocation.getMethod());
//        System.out.println("----------"+invocation.getMethod().getParameterTypes() == null ? "" :invocation.getMethod().getParameterTypes()[0]);
        System.out.println("class:-----------"+invocation.getThis().getClass());
        if(rateLimiter.tryAcquire()){
            System.out.println("----接收请求");
            return invocation.proceed();
        }else{
            System.out.println("----放弃请求。。。。");
            return "errors/404";
        }
    }

}
