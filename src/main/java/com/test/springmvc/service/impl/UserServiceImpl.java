/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.test.base.exception.BusinessRuntimeException;
import com.test.springmvc.model.User;
import com.test.springmvc.service.IUserService;

/**
 *
 * @author zengzw
 * @date 2015年6月25日
 */
@Service
public class UserServiceImpl implements IUserService{

    @Override
    public void getUserList(User user) {
        throw new BusinessRuntimeException("00001","default message");
    }

}
