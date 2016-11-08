/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.base.constants.BaseConstant;
import com.test.commom.web.source.service.IMessageSourceService;

/**
 * 返回信息封装对象
 * 
 * @author cameldeng 2013-6-15
 * @author sunkey
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMessage implements Serializable {

    /**
     * 序列码
     */
    @JsonIgnore
    private static final long serialVersionUID = 1L;

    /** 结果，成功或者失败，true/false */
    private boolean hasError = false;

    /**
     * 国际化配置 key
     */
    private String code;
    /**
     * 国际化信息参数
     */
    private Object[] args;

    /** 错误集合，有时可能一个操作返回多个错误 **/
    private final List<ResponseError> failures = new ArrayList<ResponseError>();

    /** 任何传入页面的数据对象 */
    private final Map<String, Object> dataMap = new HashMap<String, Object>();

    @JsonIgnore
    private IMessageSourceService messageSourceService;

    public ResponseMessage() {

    }

    public ResponseMessage(IMessageSourceService messageSourceService) {
        this.messageSourceService = messageSourceService;
    }

    public boolean isHasError() {
        return failures.isEmpty() ? hasError : true;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCode(String code, Object... args) {
        this.code = code;
        this.args = args;
    }

    /**
     * 国际化，返回提示信息
     * 
     * @return
     */
    public String getMessage() {
        if (messageSourceService != null && StringUtils.isNotBlank(code)) {
            return messageSourceService.getI18nMsg(code, args);
        } else if (StringUtils.isBlank(code)) {
            return getFailureString();
        }
        return code;
    }

    public List<ResponseError> getFailures() {
        return failures;
    }

    public void setFailures(List<ResponseError> failures) {
        this.failures.clear();
        if (failures != null) {
            this.failures.addAll(failures);
        }
    }

    /**
     * 封装单个错误信息
     * 
     * @param strFailureCode
     */
    public void setFailure(String strFailureCode) {
        addError(strFailureCode);
    }

    /**
     * 返回单个错误信息
     */
    public String getFailureCode() {
        return (!CollectionUtils.isEmpty(failures) ? failures.get(0).getCode() : null);
    }

    /**
     * 添加一个错误对象（error）
     * 
     * @param error
     */
    public void addError(ResponseError error) {
        failures.add(error);
    }

    /**
     * 添加一个错误信息
     * 
     * @param code
     * @param errorMessage
     */
    public void addErrorMessage(String errorMessage) {
        failures.add(new ResponseError(errorMessage, null));
    }

    /**
     * 添加一个错误信息
     * 
     * @param code
     * @param param 用于替换{*}
     */
    public void addError(String code, Object... param) {
        failures.add(new ResponseError(code, null, param));
    }

    /**
     * 添加一个错误信息
     * 
     * @param code
     * @param param 用于替换{*}
     */
    public void addErrorWithMessage(String code, String message) {
        ResponseError error = new ResponseError(code);
        error.setErrorMessage(message);
        failures.add(error);
    }

    /**
     * 添加一个错误信息
     * 
     * @param code
     * @param businessId 业务标识
     * @param param 用于替换{*}
     */
    public void addErrorWithId(String code, String businessId, Object... param) {
        failures.add(new ResponseError(code, businessId, param));
    }

    /**
     * 添加一个错误信息
     * 
     * @param code
     * @param errorMessage
     */
    public void addError(String code) {
        failures.add(new ResponseError(code));
    }

    /**
     * 传递后台参数集合，没有国际化
     * 
     * @return
     */
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap.clear();
        if (dataMap != null) {
            this.dataMap.putAll(dataMap);
        }
    }

    public void putData(String key, Object value) {
        dataMap.put(key, value);
    }

    public IMessageSourceService getMessageSourceService() {
        return messageSourceService;
    }

    public void setMessageSourceService(IMessageSourceService messageSourceService) {
        this.messageSourceService = messageSourceService;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    /**
     * 返回错误信息
     * 
     * @return
     */
    public String getFailureString() {
        if (failures.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (ResponseError responseError : failures) {
            if (sb.length() > 0) {
                sb.append(BaseConstant.SYMBOL_HTML_BR);
            }
            if (StringUtils.isNotBlank(responseError.getBusinessId())) {
                sb.append(responseError.getBusinessId()).append(BaseConstant.SYMBOL_COLON);
            }

            // if (StringUtils.isNotBlank(responseError.getCode())) {
            // sb.append(responseError.getCode()).append("^");
            // }
            if (messageSourceService != null && StringUtils.isNotBlank(responseError.getCode())) {
                sb.append(messageSourceService.getI18nMsg(responseError.getCode(), responseError.getParam()));
            } else {
                sb.append(responseError.getErrorMessage());
            }

        }
        return sb.toString();
    }
}
