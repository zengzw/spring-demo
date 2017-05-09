/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.adapter;

import java.io.FileOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;


/**
 * 适配器
 * 
 * @author zengzw
 * @date 2017年2月13日
 */
public class Adapter implements Target{

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }



    @Override
    public void say() {
        adaptee.sayBasic();
        System.out.println("我拥有更高级的功能");
    }


    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();

        Target target = new Adapter(adaptee);
        target.say();
        

    }
}
