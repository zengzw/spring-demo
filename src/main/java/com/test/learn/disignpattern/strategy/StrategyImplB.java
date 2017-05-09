/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.strategy;

/**
 *
 * @author zengzw
 * @date 2017年2月14日
 */
public class StrategyImplB implements IStrategy{

    @Override
    public void say(double price) {
      System.out.println("StrategyImpl B:"+(price * 4));
        
    }

}
