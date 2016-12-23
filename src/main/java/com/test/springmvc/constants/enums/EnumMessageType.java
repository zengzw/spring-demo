/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.constants.enums;

import org.apache.zookeeper.Watcher.Event;

/**
 *
 * @author zengzw
 * @date 2016年12月20日
 */
public enum EnumMessageType{

    /**
     * 消息
     */
    text("text"),

    /**
     * 语音
     */
    voice("voice"),
    
    /**
     * 视频消息
     */
    video("video"),

    /**
     * 小视频
     */
    shortvideo("shortvideo"),

    /**
     * 地理位置消息
     */
    location("location"),
    /**
     * 链接消息
     */
    link("link"),

    /**
     * 图片
     */
    image("image"),
    
    /**
     * 事件
     */
    Event("event");

    private String type;

    EnumMessageType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
