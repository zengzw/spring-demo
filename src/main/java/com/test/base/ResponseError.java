/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.base;

import java.io.Serializable;

/**
 * 返回对象中的错误信息封装类
 * 
 * @author dengqb
 * @date 2014年3月5日
 */
public class ResponseError implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 错误代码 */
    private String code;
    /** 错误信息 */
    private String errorMessage;
    /** 占位符数组 */
    private Object[] param;
    /** 错误堆栈信息 */
    private Exception exception;

    private String businessId;

    public ResponseError() {
    }

    public ResponseError(String code) {
        this.code = code;
    }

    public ResponseError(String code, String businessId, Object... param) {
        this.code = code;
        this.businessId = businessId;
        this.param = param;
    }

    public ResponseError(String errorMessage, Exception e) {
        this.errorMessage = errorMessage;
        this.exception = e;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object[] getParam() {
        return param;
    }


    public void add(String code, String message) {
        this.code = code;
        this.errorMessage = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

}
