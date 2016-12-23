/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.model.weixin.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author zengzw
 * @date 2016年12月19日
 */
@XmlRootElement
public class RespUserMessage {

 
    private String toUserName;
    
 
    private String fromUserName;
 
    private String createTime;
    
 
    private String msgType;
    
  
    private String content;
    
    
    private String msgId;

    @XmlElement(name="Content")
    public String getContent() {
        return content;
    }

    
    @XmlElement(name="CreateTime")
    public String getCreateTime() {
        return createTime;
    }

    @XmlElement(name="FromUserName")
    public String getFromUserName() {
        return fromUserName;
    }


    public String getMsgId() {
        return msgId;
    }

    @XmlElement(name="MsgType")
    public String getMsgType() {
        return msgType;
    }

    @XmlElement(name="ToUserName")
    public String getToUserName() {
        return toUserName;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }


    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }


    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }


    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}
