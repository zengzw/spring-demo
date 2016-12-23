/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.xml.jaxb.bean;

import com.test.springmvc.utils.JaxbUtil;

/**
 *
 * @author zengzw
 * @date 2016年12月20日
 */
public class TestJaxb {

    public static void main(String[] args) {
        
        JaxBStudeng studeng = new JaxBStudeng();
        studeng.setAge(22);
        studeng.setGrade("grade");
        studeng.setName("name");
       String xmlString =  JaxbUtil.convertToXml(studeng);
       
       System.out.println(xmlString);
    }
}
