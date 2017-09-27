/*
 * @Project Name: springmvcdemo
 * @File Name: RpcBootStrap.java
 * @Package Name: com.test
 * @Date: 2017年9月26日下午12:12:31
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月26日下午12:12:31
 * @see
 */
public class RpcBootStrap {
	
	public static void main(String[] args) {

//		
		  
		ClassPathXmlApplicationContext fileSystemXmlApplicationContext = new ClassPathXmlApplicationContext("xml/applicationContext.xml");
	}
}
