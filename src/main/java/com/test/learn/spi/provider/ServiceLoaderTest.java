/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.spi.provider;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * @author zengzw
 * @date 2017年6月14日
 */
public class ServiceLoaderTest {

    public static void main(String[] args) {
        ServiceLoader<SimpleServiceProvider> serviceLoader = ServiceLoader.load(SimpleServiceProvider.class);
       Iterator iterator =  serviceLoader.iterator();
       System.out.println(iterator.hasNext());
       while(iterator.hasNext()){
           SimpleServiceProvider provider = (SimpleServiceProvider) iterator.next();
           provider.simpleService();
       }
    }
}
