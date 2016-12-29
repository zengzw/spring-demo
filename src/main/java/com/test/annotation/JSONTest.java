/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.annotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author zengzw
 * @date 2016年12月28日
 */
public class JSONTest {
    
    protected  String  name = "dd";
    
    
    protected int age;


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }
    
    public static void main(String[] args) {
        JSONTest test = new JSONTest();
        test.name ="ddd";
        test.age= 20;
        System.out.println(JSON.toJSONString(test));
    }

}
