/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import clojure.lang.Obj;


/**
 *
 * @author zengzw
 * @param <T>
 * @date 2017年2月20日
 */
public class LogProxy<T> implements InvocationHandler{

    private T targetObject;


    public LogProxy(T object){
        this.targetObject = object;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----"+org.springframework.util.StringUtils.arrayToCommaDelimitedString(args));
        Object result = method.invoke(this.targetObject, args);
        System.out.println("----"+result.toString());
        return result;
    }
    
    
    public T getProxy(){
        T newProxyInstance = (T) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),this);
        return newProxyInstance;
    }
    
    
}
