/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.base.exception;

import org.apache.commons.lang3.ArrayUtils;


/**
 * 业务异常(checked exception)
 * 
 * @author Steven Deng
 * @version 1.0
 * @since 1.0
 * @date 2013-11-7
 * 
 */
public abstract class BusinessException extends Exception {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -3071862480832522058L;

    /**
     * 日志
     */

    /**
     * 异常代码<br>
     * 
     * 用于定位一个或一类异常，从资源文件中查找异常信息返回给用户
     * 
     */
    private String errCode;

    /**
     * 异常消息<br>
     * 
     * 这个异常消息只用于输出日志，或者在API里使用，返回给用户的消息通过errCode查找资源文件获取
     */
    private String errMsg;

    /**
     * 消息参数<br>
     * 
     * 传递异常消息需要的参数
     */
    private Object[] params;

    /**
     * 构造函数
     * 
     * @param errCode 异常代码
     * @param errMsg 异常消息
     */
    public BusinessException(String errCode, String errMsg) {
        this(errCode, errMsg, (Object[]) null);
    }

    /**
     * 构造函数
     * 
     * @param errCode 异常代码
     * @param errMsg 异常消息
     * @param params 消息参数
     */
    public BusinessException(String errCode, String errMsg, Object[] params) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.params = ArrayUtils.clone(params);
    }

    /**
     * 构造函数
     * 
     * @param errCode 异常代码
     * @param errMsg 异常消息
     * @param params 消息参数
     * @param throwable 上层异常
     */
    public BusinessException(String errCode, String errMsg, Object[] params, Throwable throwable) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.params = ArrayUtils.clone(params);
    }

    /**
     * 构造函数
     * 
     * @param errCode 异常代码
     * @param errMsg 异常消息
     * @param throwable 上层异常
     */
    public BusinessException(String errCode, String errMsg, Throwable throwable) {
        this(errCode, errMsg, (Object[]) null, throwable);
    }

    /**
     * 转换为unchecked异常
     * 
     * @return unchained异常
     */
    public BusinessRuntimeException unchecked() {
        return new BusinessRuntimeException(this.errCode, this.errMsg, this.params, this);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = ArrayUtils.clone(params);
    }

}
