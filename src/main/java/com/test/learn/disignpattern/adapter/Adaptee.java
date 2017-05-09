/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.adapter;

/**
 * 原来基本实现功能，不能满足未来的变化，需要增加一个适配器来适应当前的功能。
 * 
 * 
 * @author zengzw
 * @date 2017年2月13日
 */
public class Adaptee{

    public void sayBasic() {
        System.out.println("我拥有基本的功能！");
    }

}
