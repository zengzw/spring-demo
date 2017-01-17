/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author zengzw
 * @date 2017年1月13日
 */
public class JsoupTest {

    public static void main(String[] args) {
        Document doc;
        try {
         /*   doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
            String text = doc.outerHtml();
            System.out.println(text);*/
            String text = "水电费发送到发送到";
            
            System.out.println(Jsoup.parse(text).text());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
