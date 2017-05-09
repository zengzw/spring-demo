/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.state;

/**
 *  状态 模式工厂
 *  
 *  
 * @author zengzw
 * @date 2017年2月16日
 */
public class FactoryBuilder {
    
    public static State buildState(int status){
        if(status == ConstantStatus.State.initial){
            return new InitialState();
        }
        
        return  new PayFaileState();
    }

}
