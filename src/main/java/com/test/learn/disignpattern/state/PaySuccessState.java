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
public class PaySuccessState implements State{

    @Override
    public int current() {
      return   ConstantStatus.State.pay_success;
    }

    @Override
    public void supportUpdates(Context context) {
   
    }

}
