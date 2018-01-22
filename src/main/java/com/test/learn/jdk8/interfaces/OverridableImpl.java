/*
 * @Project Name: springmvcdemo
 * @File Name: OverridableImpl.java
 * @Package Name: com.test.learn.jdk8.interfaces
 * @Date: 2018年1月18日上午10:02:14
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.interfaces;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月18日上午10:02:14
 * @see
 */
public class OverridableImpl implements Defaulable{
	
	@Override
	public String noRequired() {
	
		return "override implemention";
	}
	
	
}
