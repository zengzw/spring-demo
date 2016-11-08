/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.console;

import java.util.Date;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2014年10月14日
 */
@RestController
public class ResetControler {

    @RequestMapping(value="/reset",method=RequestMethod.GET)
    public User view(){
        User user = new User();
        user.setId(12);
        user.setDateTime(new Date());
        user.setName("name");
        user.setNickName("nickName");
        
        return user;
        
    }
    
    
    @RequestMapping("/reset2")
    public String view2(){
        
        return "test resetcontroller";
    }
    
    @RequestMapping("/api")
    public Callable<User> api(){
        System.out.println("== hello");
        
        return new Callable<User>() {

            @Override
            public User call() throws Exception {
                Thread.sleep(10L * 1000);
                User user = new User();
                user.setId(111);
                user.setName("name");
                user.setNickName("nickName");
                
                return user;
            }
        };
    }
    
    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        
        ListenableFuture<ResponseEntity<User>> future = template.getForEntity("http://localhost:8090/api", User.class);
        future.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {

            @Override
            public void onFailure(Throwable arg0) {
                System.out.println("--------"+arg0.getMessage());
                
            }

            @Override
            public void onSuccess(ResponseEntity<User> response) {
                System.out.println("---"+response.getBody());
                
            }
        });
        
        System.out.println("===not wait");
        
       
    }
}
