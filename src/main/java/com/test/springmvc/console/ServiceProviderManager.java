/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.console;

import java.util.List;

/**
 *
 * @author zengzw
 * @date 2017年2月13日
 */
public class ServiceProviderManager {
    
    private static ThreadLocal<List<String>> local = new ThreadLocal<List<String>>();
    
 
    public static void add(List<String> values){
        local.set(values);
    }
    
    
    public static List<String> get(){
        return local.get();
    }
  
    
    public static void remove(){
        local.remove();
    }
   
}
