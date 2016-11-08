/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.qz.job.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;

import com.test.qz.job.bean.ScheduleJob;

/**
 *
 * @author zengzw
 * @date 2016年4月6日
 */
public class TaskJobUtils {

    public static final String STATUS_RUNNING = "1";  
    
    public static final String STATUS_NOT_RUNNING = "0"; 
    
    public static final String CONCURRENT_IS = "1"; 
    
    public static final String CONCURRENT_NOT = "0";  
    
    
    public static void invokeMethod(ScheduleJob job) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        
        Class clazz = Class.forName(job.getBeanClass());
        Object object = clazz.newInstance();
        
        if(object != null){
            clazz = object.getClass();
            Method method = clazz.getDeclaredMethod(job.getMethedName());
            
            if(method != null){
                method.invoke(object);
            }
        }
        
    }
}
