/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.service;

import org.springframework.stereotype.Service;

import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2015年6月25日
 */
public interface IUserService {
    
    public void getUserList(User user);

}
