/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.spring.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StringUtils;


/**
 *
 * @author zengzw
 * @date 2017年2月27日
 */
public class TicketServiceAroundAdvice implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] args = invocation.getArguments();

        System.out.println("---args:"+StringUtils.arrayToCommaDelimitedString(args));
        String methodName = invocation.getMethod().getName();
        System.out.println("---around_advice:begin...."+methodName);  
        Object returnValue = invocation.proceed();  
        System.out.println("---around_advice:end....."+methodName);  
        
        return returnValue;  
    }

}
