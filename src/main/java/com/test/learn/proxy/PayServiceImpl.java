/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.proxy;

import java.lang.reflect.Proxy;

/**
 *
 * @author zengzw
 * @date 2017年2月20日
 */
public class PayServiceImpl implements IPayServcie{

    @Override
    public String pay(String params) {
        return "paying " + params;
    }

    public static void main(String[] args) {
        proxy2();
        
    }
    
    public  static void proxy1(){
      IPayServcie iPayServcie = new PayServiceImpl();
        
        LogProxy proxy = new LogProxy(iPayServcie);
        
        IPayServcie iPayServcie2 = (IPayServcie)Proxy.newProxyInstance(iPayServcie.getClass().getClassLoader(), iPayServcie.getClass().getInterfaces(),proxy);
        System.err.println(iPayServcie2.pay("hello"));
    }
    
    public  static void proxy2(){
        LogProxy proxy = new LogProxy<IPayServcie>(new PayServiceImpl());
        IPayServcie iPayServcie = (IPayServcie) proxy.getProxy();
        System.err.println(iPayServcie.pay("hello"));
        
    }
}
