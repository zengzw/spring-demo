/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.reflect.entity;

/**
 *
 * @author zengzw
 * @date 2017年6月30日
 */
public class AOPUserProxy implements Proxy{

    @Override
    public void getProxy() {
       System.out.println("----proxy---");
    }

    
}
