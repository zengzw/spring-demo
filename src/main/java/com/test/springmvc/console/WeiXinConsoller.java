/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.springmvc.service.WeiXinService;


/**
 *
 * @author zengzw
 * @date 2016年6月7日
 */
@Controller
public class WeiXinConsoller {


    @Autowired
    WeiXinService service;
    /* *//**
     * 配置Url 成为开发者
     * 
     * @param request
     * @param response
     * @throws IOException
     *//*
    @RequestMapping(value="/weixin",method = RequestMethod.GET)
    public void getWeiXin(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");    

        System.out.println("-----> signature:"+signature +" timestamp:"+timestamp +" nonce:"+nonce +" echostr:"+echostr);

        PrintWriter writer = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SingUtil.checkSignature(signature, timestamp, nonce)) {  
            writer.print(echostr);  
        }  
        else {
            writer.print("");
        }


    }
      */

    /**
     * 配置Url 成为开发者
     * 
     * @param request
     * @param response
     * @throws Exception 
     */
    @RequestMapping(value="/weixin",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getMessage(HttpServletRequest request,HttpServletResponse response) throws Exception {
        InputStream inputStream = request.getInputStream();

        String responeMsg = service.handleRequest(inputStream);

        System.out.println("-----返回消息："+responeMsg);

        /*   // 响应消息
        PrintWriter out = response.getWriter();
        out.print(responeMsg);

        out.close();*/
        return responeMsg;
        //        return "success";

    }

    String inputStream2String(InputStream is) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();
    }


}
