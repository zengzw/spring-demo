/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.base;




/**
 *  系统级编码
 * 
 * @author sunkey
 * @date Oct 17, 2014
 */
public interface SystemMessageCode extends MessageCode {

    /**
     * 系统错误
     */
    String ERROR = SYSTEM + "001";

    /**
     * 系统繁忙
     */
    String BUSY = SYSTEM + "002";
    
    /**
     * Json解析异常
     */
    String JSON_PARSE_EXCEPTION = SYSTEM + "003";

    /**
     * 会话超时
     */
    String SESSION_TIMEOUT = SYSTEM + "004";
    
    /**
     * HTTP消息不能读取
     */
    String HTTP_MESSAGE_NOT_READABLE = SYSTEM + "005";

    /**
     * 数据重复
     */
    String DUPLICATE_DATA = SYSTEM + "006";

    /**
     * 不支持的媒体类型
     */
    String NOT_SUPPORTED_MEDIA_TYPE = SYSTEM + "007";

    /**
     * 数据绑定错误
     */
    String DATA_BIND_ERROR = SYSTEM + "008";

}