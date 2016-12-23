/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.model.weixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.test.springmvc.model.weixin.response.RespUserMessage;
import com.test.springmvc.utils.JaxbUtil;

/**
 *
 * @author zengzw
 * @date 2016年12月20日
 */
public class MainTest {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("D://test.xml"));
        
        JaxbUtil.fromXML(fileInputStream, RespUserMessage.class);
    }
}
