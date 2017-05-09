/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.other;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 *
 * @author zengzw
 * @date 2017年5月3日
 */
public class HttpUnitTest {
    
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        WebClient webClient = new WebClient();
        HtmlPage page1 = (HtmlPage)webClient.getPage( "http://www.baidu.com");
        System.out.println(page1);
         
    }

}
