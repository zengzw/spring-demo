/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.state;

/**
 *
 *
 *
 *
 *
 *
 *
 * @author zengzw
 * @date 2017年2月16日
 */
public class Test {
    
    public static void main(String[] args) {
         Context context = new Context();
         State state = FactoryBuilder.buildState(ConstantStatus.State.initial);
         state.supportUpdates(context);
         
         //判断是否能支持 从 支付中 修改成 退款中的状态
         System.out.println("----"+context.support(ConstantStatus.State.refund_success));
    }
}
