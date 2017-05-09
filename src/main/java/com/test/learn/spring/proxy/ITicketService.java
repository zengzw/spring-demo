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
public interface ITicketService {
    //售票  
    public void sellTicket(String name);  
  
    //问询  
    public void inquire(User user);  
  
    //退票  
    public void withdraw();  
}
