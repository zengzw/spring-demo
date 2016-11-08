/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.commom.web.source.service.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.test.commom.web.source.service.IMessageSourceService;

public class MessageSourceServiceImpl implements IMessageSourceService {
    /**
     * 加载资源配置
     */
    private MessageSource messageSource;

    @Override
    public String getI18nMsg(HttpServletRequest request, String errorCode, Object... args) {
        return getI18nMsg(getCurrentLocale(request), errorCode, args);
    }

    @Override
    public String getI18nMsg(String errorCode, Object... args) {
        return getI18nMsg(getCurrentLocale(), errorCode, args);
    }

    @Override
    public String getI18nMsg(Locale locale, String errorCode, Object... args) {
        return messageSource.getMessage(errorCode, args, locale);
    }

    @Override
    public Locale getCurrentLocale(HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        return null == locale ? Locale.getDefault() : locale;
    }

    @Override
    public Locale getCurrentLocale() {
        HttpServletRequest request = getHttpServletRequest();
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        return null == locale ? Locale.getDefault() : locale;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
}
