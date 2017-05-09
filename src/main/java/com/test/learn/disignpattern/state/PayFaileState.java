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
public class PayFaileState implements State{

    @Override
    public int current() {
        return ConstantStatus.State.pay_fail;
    }

    @Override
    public void supportUpdates(Context context) {
        // TODO Auto-generated method stub
        
    }

}
