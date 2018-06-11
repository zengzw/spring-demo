/*
 * @Project Name: springmvcdemo
 * @File Name: SystemTest.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2018年6月8日下午2:36:36
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月8日下午2:36:36
 * @see
 */
public class SystemTest {
	
	public static void main(String[] args) {

		int processors = Runtime.getRuntime().availableProcessors(); // CPU的核心数
		System.err.println(processors);
	}
}
