/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.spring.proxy;

import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2017年2月27日
 */
public class RailwayStation implements ITicketService{

    public void sellTicket(String name){  
        System.out.println("售票............"+name);  
    }  

    public void inquire(User user) {  
        System.out.println("问询.............");  
    }  

    public void withdraw() {  
        System.out.println("退票.............");  
    }  
}
