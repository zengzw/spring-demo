/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.httpunit;

import java.io.IOException;
import java.net.MalformedURLException;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;

/**
 *
 * @author zengzw
 * @date 2017年5月8日
 */
public class TestHttpUnit {



    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
        final WebClient webClient=new WebClient(BrowserVersion.CHROME); //chrom 浏览器
        webClient.getOptions().setCssEnabled(false);//警用CSS
        webClient.getOptions().setJavaScriptEnabled(true);//启用js
        webClient.getOptions().setRedirectEnabled(true);//3 启动客户端重定向

        // 4 运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 5 设置超时
        webClient.getOptions().setTimeout(50000);
        //6 设置忽略证书
        //webClient.getOptions().setUseInsecureSSL(true);
        //7 设置Ajax
        //webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置cookie
        webClient.getCookieManager().setCookiesEnabled(true);

        final HtmlPage page = webClient.getPage("http://auc.tsh.com/views/login.html");
        //        等待JS执行完成，包括远程JS文件请求，Dom处理  
        if(page.isHtmlPage()){
            webClient.waitForBackgroundJavaScript(10000);
        }

        HtmlTextInput  loginName  = (HtmlTextInput) page.getElementById("loginName");
        HtmlPasswordInput  password  = (HtmlPasswordInput) page.getElementById("passWord");
        DomNodeList<DomElement> lstDomNodeList =  page.getElementsByTagName("input");
        loginName.setValueAttribute("admin");
        password.setValueAttribute("admin123");

        //登录后，得到第二个页面
        HtmlPage page2 = null;
        for(DomElement el:lstDomNodeList){
            String value  = el.getAttribute("value");
            if(value.equals("登录")){
                System.out.println("--------");
                HtmlButtonInput button = (HtmlButtonInput)el;
                page2 = button.click();
            }
        }

//      等待JS执行完成，包括远程JS文件请求，Dom处理  
      if(page2.isHtmlPage()){
          webClient.waitForBackgroundJavaScript(20000);
      }

    //    page2 = webClient.getPage("http://auc.tsh.com/views/home.html");
        System.out.println(page2.asXml());

        
        //获取cookie
        java.util.Set<Cookie> cookies = webClient.getCookieManager().getCookies();
        for(Cookie ck : cookies){
            System.out.println(JSON.toJSONString(ck));
        }

    }
}
