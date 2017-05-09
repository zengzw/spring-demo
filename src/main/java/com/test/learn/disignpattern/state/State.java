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
public interface State {


    /**
     * 当前状态
     * @return
     */
    public int current();
    
    /**
     * zhi
     * @param states
     * @return
     */
    public void supportUpdates(Context context);
    
}
