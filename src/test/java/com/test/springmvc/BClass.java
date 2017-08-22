/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author zengzw
 * @date 2017年6月7日
 */
public class BClass extends AbstractClass{

    {
        System.out.println(" B {}");
    }

    static{
        System.out.println(" B static {}");
    }

    /**
     * 
     */
    public BClass() {
        System.out.println("BClass()");
    }

    public static void main(String[] args) {
        AbstractClass cc = new BClass();
        
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,4,0,TimeUnit.HOURS,new ArrayBlockingQueue<Runnable>(10));
    
        
    }
}
