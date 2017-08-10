/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

/**
 *
 * @author zengzw
 * @date 2017年6月7日
 */
public abstract class AbstractClass {

    {
        System.out.println(" A {}");
    }
    
    static{
        System.out.println(" Astatic {}");
    }
    
    /**
     * 
     */
    public AbstractClass() {
     System.out.println("AbstractClass()");
    }
    
    public void testA(){
        System.out.println("abstract...");
    }
}
