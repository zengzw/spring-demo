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
public class AndroidChargerImpl implements IAndroidUSB{

    @Override
    public void chargeWithAndroidUSB() {
       System.out.println("----使用Android USB 充电");
    }

}
