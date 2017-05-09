/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.adapter.charter2;

/**
 *
 * @author zengzw
 * @date 2017年2月14日
 */
public class Iphone7{

    private IAppleUSB appleUSB;
    
    public Iphone7(IAppleUSB appleUSB){
        this.appleUSB = appleUSB;
    }
    
    public void charge(){
        System.out.println("开始给Iphone7充电");
        appleUSB.chargeWithLighting();
        System.out.println("结束充电");
    }
    
    
}
