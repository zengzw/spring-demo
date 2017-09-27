/*
 * @Project Name: springmvcdemo
 * @File Name: AfterProxy.java
 * @Package Name: com.test.learn.proxy.chain.impl
 * @Date: 2017年9月19日下午4:41:19
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxy.chain.impl;

import java.lang.reflect.Method;

import com.test.learn.proxy.chain.AbstractProxy;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月19日下午4:41:19
 * @see
 */
public class AfterProxy extends AbstractProxy{
	
	@Override
	public void after(Class<?> cls, Method method, Object[] params) {
		
		System.out.println("--invoke after proxy");
	}
	
	
}
