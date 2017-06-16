/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.spring.sources;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.test.springmvc.model.TestUser;

/**
 *
 * @author zengzw
 * @date 2017年5月19日
 */
public class TestResource {
    
    public static void main(String[] args) {
        ApplicationContext ctx =   new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");//读取bean.xml中的内容
        
        TestUser testUser = ctx.getBean("testUser",TestUser.class);
        
        System.out.println(testUser.getAge());
    }

    
    
    public static void  classPathResource(){
        
    }
}
