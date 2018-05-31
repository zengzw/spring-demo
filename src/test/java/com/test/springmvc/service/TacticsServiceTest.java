/*
 * @Project Name: springmvcdemo
 * @File Name: TacticsServiceTest.java
 * @Package Name: com.test.springmvc.service
 * @Date: 2018年5月31日下午4:09:22
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.test.springmvc.TestWithSpring;
import com.test.springmvc.model.TrainTactics;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月31日下午4:09:22
 * @see
 */
public class TacticsServiceTest extends TestWithSpring{
	
	@Autowired
	private TrainTacticsService tacticsService;
	
	@Test
	public void testIsNull(){
		System.out.println("----->"+tacticsService);
	}
	
	@Test
	public void testGetId(){
		TrainTactics tactics = tacticsService.getByid(13);
		System.out.println(JSON.toJSONString(tactics));
	}
}
