/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.base;

/**
 * 消息编码一级前缀
 * 
 * @author sunkey
 * @date Oct 17, 2014
 */
public interface MessageCode {
   
    /**
     * 系统消息
     */
    String SYSTEM = "00";
    
    /**
     * 公共消息
     */
    String COMMON = "10";

    /**
     * 帐户
     */
    String ACCOUNT = "13";

}
