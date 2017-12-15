/*
 * @Project Name: springmvcdemo
 * @File Name: SpringRetryTest.java
 * @Package Name: com.test.springmvc.test
 * @Date: 2017年12月13日下午6:45:26
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.learn.springretry.RetryRemoteService;
import com.test.springmvc.TestWithSpring;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年12月13日下午6:45:26
 * @see
 */
public class SpringRetryServiceTest extends TestWithSpring {

	@Autowired
	RetryRemoteService remoteService;


	@Test
	public void testOne() throws Exception{
		System.out.println("--------------");

		remoteService.call();
	}


	@Test
	public void testCircuitBreaker(){
		for(int i = 0; i<= 10; i++){
			remoteService.circuitBreaker(i);
		}
	}
}
