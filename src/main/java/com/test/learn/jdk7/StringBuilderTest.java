/*
 * @Project Name: springmvcdemo
 * @File Name: StringBuilderTest.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2018年5月2日上午11:23:09
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7;

import java.util.Arrays;


/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月2日上午11:23:09
 * @see
 */
public class StringBuilderTest {
	
	
	public static void main(String[] args) {

		StringBuilder builder = new StringBuilder();
		builder.append("dd");
		
		String st = "asdas";
		System.err.println(st.length());
		
		char[] chars = new char[2];
		
		chars = Arrays.copyOf(chars, 10);
		System.out.println(chars.length);
		chars = Arrays.copyOfRange(chars, 1, 3);
		System.out.println(chars.length);
		
	}
}
