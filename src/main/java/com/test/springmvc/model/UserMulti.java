/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.model;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.test.springmvc.group.First;
import com.test.springmvc.group.Second;

/**
 * 分组验证
 * @author zengzw
 * @date 2014年10月11日
 * 指定顺序验证
 * 先验证UserMutil 没分组的，在验证first组，在验证second 
 */
@GroupSequence({First.class,Second.class,UserMulti.class})
public class UserMulti implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull(groups={First.class},message="不能为空！")
    @Min(value=20,groups={Second.class},message="{user.id}")
    private Integer id;

    

    @NotEmpty(message="{user.name.empty}")
    @Size(min=2,message="name size 2")
    private String name;
    
    @NotNull
    @Min(value=20,groups={Second.class},message="{user.age.size}")
    private Integer age;

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
