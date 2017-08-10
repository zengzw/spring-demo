/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.test.springmvc.model.User;
import com.test.springmvc.model.weixin.AccessToken;
import com.test.springmvc.service.WeiXinService;
import com.test.springmvc.utils.HttpUtils;

/**
 *
 * @author zengzw
 * @date 2016年12月19日
 */
public class WeiXinTest{
    
    WeiXinService service = new WeiXinService();
    
    @Test
    public void testAccessToken(){
       AccessToken token = service.getAccessToken();
        System.out.println("----------"+JSON.toJSONString(token));
    }
    
    
    @Test
    public void testGetIp(){
        String ip = service.getCallbackIp();
        System.out.println("----------"+ip);
    }
    
    
    @Test
    public void testGetUserList(){
        String result = service.getUserList();
        System.out.println("----------"+result);
    }
    
    
    @Test
    public void testGetUserInfo(){
        String result = service.getUserInfo();
        System.out.println("----------"+result);
    }
    
    @Test
    public void testCreateMenuItem(){
        String result = service.createMenuItem();
        System.out.println("----------"+result);
    }
    
    
    @Test
    public void testCreateItemScancodeWaitmsg(){
        String result = service.createItemScancodeWaitmsg();
        System.out.println("----------"+result);
    }
    
    
    @Test
    public void testDeleteMenuItem(){
        String result = service.deleteMenuItem();
        System.out.println("----------"+result);
    }
    
    
    @Test
    public void testWeiXin(){
//        HttpUtils.doGet("http://phoenixtea.oicp.net/weixin", null);
        HttpUtils.doPost("http://phoenixtea.oicp.net/weixin", null);
    }
    
    
    public static void main(String[] args) {
     
//        System.out.println(Thread.currentThread().getContextClassLoader().g   et);
        
        List<? super Number> list1 = new ArrayList<Number>();
        list1.add(2);
        

      
    }
    
   class Ac implements   Callable<User>{

    @Override
    public User call() throws Exception {
        return null;
    }
       
   }
}
