/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.decorator;

/**
 *
 * @author zengzw
 * @date 2017年2月15日
 */
public class BreakTea implements IByTea{

    @Override
    public void buy() {
       System.out.println("这里卖的是黑茶");
        
    }

}
