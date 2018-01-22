/*
 * @Project Name: springmvcdemo
 * @File Name: DefaulableTest.java
 * @Package Name: com.test.learn.jdk8.interfaces
 * @Date: 2018年1月18日上午10:04:40
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
 * @date 2018年1月18日上午10:04:40
 * @see
 */
public class DefaulableTest {
	
	public static void main(String[] args) {

		Defaulable defaulable = DefaulableFactory.create(DefaulableImpl::new);
		System.out.println(defaulable.noRequired());
		
		defaulable = DefaulableFactory.create(OverridableImpl::new);
		System.out.println(defaulable.noRequired());
	}
}

