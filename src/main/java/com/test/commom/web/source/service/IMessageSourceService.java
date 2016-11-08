/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.commom.web.source.service;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * 消息资源服务 ,取请求中本地化，根据
 * 
 * @author sunkey
 * @date Sep 30, 2014
 */
public interface IMessageSourceService {
    
    /**
     * 获取本地化消息
     * 
     * @param request 请求对象
     * @param key 消息key
     * @param args 参数
     * @return
     */
    String getI18nMsg(HttpServletRequest request, String key, Object... args);

    /**
     * 获取本地化消息
     * 
     * @param key 消息key
     * @param args 参数
     * @return
     */
    String getI18nMsg(String key, Object... args);

    /**
     * 获取本地化消息
     * 
     * @param locale 本地语言
     * @param key 消息key
     * @param args 参数
     * @return
     */
    String getI18nMsg(Locale locale, String key, Object... args);

    /**
     * 获取当前Locale
     * 
     * @param request
     * @return
     */
    Locale getCurrentLocale(HttpServletRequest request);
    
    /**
     * 获取当前Locale
     * 
     * @return
     */
    Locale getCurrentLocale();
}
