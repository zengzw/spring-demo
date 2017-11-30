/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.decorator;

/**
 * 茶叶装饰者
 * 
 * @author zengzw
 * @date 2017年2月15日
 */
public class BuyOtherDecorator implements IByTea{

    
    private IByTea iByTea;
    
    public BuyOtherDecorator(IByTea iByTea){
        this.iByTea = iByTea;
    }
    @Override
    public void buy() {
        System.out.println("帮忙烧水");
        iByTea.buy();
        System.out.println("回收茶叶");
        
    }
    
    
    public static void main(String[] args) {
        IByTea oolongTea = new OolongTea();
//        oolongTea.buy();
        
        System.out.println("--------------");
        
        IByTea decorator = new BuyOtherDecorator(oolongTea);
        decorator.buy();
       
    }

}
