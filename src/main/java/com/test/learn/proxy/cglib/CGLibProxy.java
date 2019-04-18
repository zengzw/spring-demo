/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.proxy.cglib;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *CGlib 代理类
 *
 * @author zengzw
 * @date 2017年6月29日
 */
public class CGLibProxy implements MethodInterceptor{


    public <T> T getProxy(Class<T> clz){
        return (T) Enhancer.create(clz,this);
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println(method.getName());

        System.out.println("cglib before");
        Object object = proxy.invokeSuper(obj, args);
        System.out.println("cglib after");

        return object;
    }

}
