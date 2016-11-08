/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.springmvc.customer.Forbidden;


/**
 *
 * @author zengzw
 * @date 2014年10月11日
 */
public class User {
    
    @Length(min=1,message="id.....")
    private int id;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    @Size(min=2,message="{user.name.empty}")
    @Forbidden(message="包含敏感词语")
    private String name;

    @Size(max=5,min=2,message="{user.nickName.size}")
    private String nickName;

    public Date getDateTime() {
        return dateTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
