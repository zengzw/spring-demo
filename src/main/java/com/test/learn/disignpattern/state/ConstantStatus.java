/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.state;

/**
 *
 * @author zengzw
 * @date 2017年2月16日
 */
public class ConstantStatus {
    
    
    public interface State{
        int initial = 0;
        
        int paying = 1;
        
        int pay_success = 2;
        
        int pay_fail  = 3;
        
        int pay_exception = 4;
        
        int refunding = 5;
        
        int refund_success = 6;
        
        int refund_fail = 7;
    }

}
