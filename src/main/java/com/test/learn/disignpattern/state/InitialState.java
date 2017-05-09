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
public class InitialState implements State{

    @Override
    public int current() {
        System.out.println("初始化状态");
        return ConstantStatus.State.initial;
    }

    @Override
    public void supportUpdates(Context context) {
         context.setState(new PayFaileState(),
                 new PaySuccessState(),new RefundSuccessState());
    }

}
