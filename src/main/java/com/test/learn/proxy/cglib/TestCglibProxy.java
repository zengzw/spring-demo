/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.proxy.cglib;

import com.test.learn.proxy.PayServiceImpl;

/**
 *
 * @author zengzw
 * @date 2017年6月29日
 */
public class TestCglibProxy {

    
    public static void main(String[] args) {
        CGLibProxy proxy = new CGLibProxy();
        PayServiceImpl payService = proxy.getProxy(PayServiceImpl.class);
        System.out.println(payService.pay("hello"));
    }
}
