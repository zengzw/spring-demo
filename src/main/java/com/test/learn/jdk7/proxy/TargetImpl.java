/*
 * @Project Name: springmvcdemo
 * @File Name: TargetImpl.java
 * @Package Name: com.test.learn.jdk7.proxy
 * @Date: 2018年5月9日下午12:26:56
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7.proxy;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月9日下午12:26:56
 * @see
 */
public class TargetImpl implements Target{

	@Override
	public void execute() {
		System.out.println("---target execute----");
	}
	
}
