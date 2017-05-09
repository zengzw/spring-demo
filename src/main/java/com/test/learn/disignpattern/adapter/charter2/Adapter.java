/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.adapter.charter2;

/**
 * 适配器，让android usb 能街接到 苹果USB中
 * 
 * @author zengzw
 * @date 2017年2月14日
 */
public class Adapter implements IAppleUSB{
    

    public IAndroidUSB androidUSB;
    
    
    public Adapter(IAndroidUSB androidUSB){
        this.androidUSB = androidUSB;
    }
    
    
    @Override
    public void chargeWithLighting() {
        System.out.println("---让android usb 使用 apple usb 充电");
        androidUSB.chargeWithAndroidUSB();
    }

    
    public static void main(String[] args) {
        Iphone7 iphone7 = new Iphone7(new AppleChargerImpl());
        iphone7.charge();
        
        
        Adapter adapter = new Adapter(new AndroidChargerImpl());
        Iphone7 i7 = new Iphone7(adapter);
        i7.charge();
    }
}
