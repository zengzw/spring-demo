/*
 * @Project Name: springmvcdemo
 * @File Name: StudyServiceImpl.java
 * @Package Name: com.test.learn.rpc.simple.service
 * @Date: 2018年3月12日下午2:20:37
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.simple.service;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月12日下午2:20:37
 * @see
 */
public class StudyServiceImpl implements StudyService{

	@Override
	public String study(String language) {

		return "study "+language;
	}
}
