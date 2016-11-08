/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.intercept;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author zengzw
 * @date 2016年5月9日
 */
public class MyIntercepter implements MethodInterceptor{


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
       
        System.out.println("----------"+invocation.getArguments());
        System.out.println("----------"+invocation.getMethod());
        System.out.println("-----------"+invocation.getThis().getClass());
        return invocation.proceed();
    }

}
