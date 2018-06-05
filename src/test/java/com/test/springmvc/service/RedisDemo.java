/*
 * @Project Name: springmvcdemo
 * @File Name: RedisDemo.java
 * @Package Name: com.test.springmvc.service
 * @Date: 2018年6月4日下午6:35:04
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.learn.redis.RedisServcieDemo;
import com.test.springmvc.TestWithSpring;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月4日下午6:35:04
 * @see
 */
public class RedisDemo extends TestWithSpring{
	
	@Autowired
	private RedisServcieDemo servcieDemo;
		
	@Test
	public void testTest(){
		servcieDemo.test();
	}
	
}
