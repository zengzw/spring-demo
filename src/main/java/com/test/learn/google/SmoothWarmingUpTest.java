/*
 * @Project Name: springmvcdemo
 * @File Name: SmoothWarmingUpTest.java
 * @Package Name: com.test.learn.google
 * @Date: 2017年12月4日下午6:31:42
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.google;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年12月4日下午6:31:42
 * @see
 */
public class SmoothWarmingUpTest {
	
	public static void main(String[] args) {

		//double permitsPerSecond 每秒新增令牌数
//		long warmupPeriod 从冷启动速率过度到平均速率的时间间隔
		
		RateLimiter limiter = RateLimiter.create(5, 1000,TimeUnit.SECONDS);
		
		for(int i = 0; i<20; i++){
			limiter.acquire();
			System.out.println("---");
		}
		
	}
}
