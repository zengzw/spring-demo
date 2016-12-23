/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.xml.xstream.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author zengzw
 * @date 2016年12月20日
 */
public class Student {

    private int age;
    
    private String name;
    
    private String grade;
    
    @XStreamAlias(value="tea")
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
