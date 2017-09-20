/*
 * @Project Name: springmvcdemo
 * @File Name: FinallyProxy.java
 * @Package Name: com.test.learn.proxy.chain.impl
 * @Date: 2017年9月19日下午4:43:29
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxy.chain.impl;

import com.test.learn.proxy.chain.AbstractProxy;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月19日下午4:43:29
 * @see
 */
public class FinallyProxy extends AbstractProxy{

	@Override
	public void end() {
		System.out.println("---invoke ended.............");
	}
}
