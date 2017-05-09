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
public class Price {

    private IStrategy strategy;
    
    
    public Price(IStrategy strategy){
        this.strategy = strategy;
    }
    
    public void calucatePrice(double price){
        strategy.say(price);
    }
    
    public static void main(String[] args) {
        IStrategy strategy = new StrategyImplA();
        IStrategy strategyB = new StrategyImplB();
        
        Price price = new Price(strategyB);
        
        price.calucatePrice(20);
    }
}
