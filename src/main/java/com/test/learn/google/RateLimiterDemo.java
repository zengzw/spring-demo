/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.google;

import com.google.common.util.concurrent.RateLimiter;

/**
 *
 * 每秒请求,限制流量
 * 
 * 
 * @author zengzw
 * @date 2017年6月21日
 */
public class RateLimiterDemo {
    
    public static void main(String[] args) {
//        testNoRateLimiter();
        
        testWithRateLimiter();
        
    }
    
    public static void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);
            
        }
        Long end = System.currentTimeMillis();
        
        System.out.println(end - start);
        
    }
    
    
    public static void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 1500; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
            
        }
        Long end = System.currentTimeMillis();
        
        System.out.println(end - start);
        
    }
    

}
