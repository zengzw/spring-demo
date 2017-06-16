/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.builder;

import com.alibaba.fastjson.JSON;

/**
 *  构建者模式
 *  
 * @author zengzw
 * @date 2017年6月15日
 */
public class EmailMessage {

    private String from;
    private String to;
    private String subject;
    private String content;
    private String mimeType;  // optional
    
    private EmailMessage(){};
    
    
    public static Builder builder(){
        return new EmailMessage.Builder();
    }
    
    
    public static class Builder{
        public Builder(){};
        
        private EmailMessage message = new EmailMessage();
        
        public Builder from(String form){
            message.from = form;
            return this;
        }
        
        public Builder to(String to){
            message.to = to;
            return this;
        }
        
        public Builder subject(String subject) {
            message.subject = subject;
            return this;
        }

        public Builder content(String content) {
            message.content = content;
            return this;
        }

        public Builder mimeType(String mimeTypeName) {
            message.mimeType = mimeTypeName;
            return this;
        }
        
        public EmailMessage build(){
            return message;
        }
        
    }
    
    
    public static void main(String[] args) {
       EmailMessage emailMessage = EmailMessage.builder().from("ddd")
       .to("to").build();
       
       System.out.println(JSON.toJSONString(emailMessage));
    }


    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from;
    }


    public String getTo() {
        return to;
    }


    public void setTo(String to) {
        this.to = to;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getMimeType() {
        return mimeType;
    }


    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

}
