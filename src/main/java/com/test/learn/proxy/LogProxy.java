/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author zengzw
 * @date 2017年2月20日
 */
public class LogProxy implements InvocationHandler{

    private Object targetObject;


    public LogProxy(Object object){
        this.targetObject = object;
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----"+org.springframework.util.StringUtils.arrayToCommaDelimitedString(args));
        Object result = method.invoke(this.targetObject, args);
        System.out.println("----"+result.toString());
        return result;
    }
    
    public  void kc(){
        kb();
    }
    
    public void kb(){
        kc();
    }
    public static void main(String[] args) {
       LogProxy logProxy = new LogProxy(new String());
//       logProxy.kb();
       while(true){
           System.out.println("-----------");
       }
    }   
}
