/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.spi.provider;

/**
 *
 * @author zengzw
 * @date 2017年6月14日
 */
public class SimpleServiceProviderImpl implements SimpleServiceProvider{

    @Override
    public void simpleService() {
        System.out.println("simpleService..........");
    }

}
