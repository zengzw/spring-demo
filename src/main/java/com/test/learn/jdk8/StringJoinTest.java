/*
 * @Project Name: springmvcdemo
 * @File Name: StringJoinTest.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年2月1日下午3:50:33
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月1日下午3:50:33
 * @see
 */
public class StringJoinTest {
	
	public static void main(String[] args) {

		StringJoiner joiner = new StringJoiner(",");
		joiner.add("a");
		joiner.add("a");
		joiner.add("a");
		
		
		System.out.println(joiner.toString());
		
		
		StringJoiner joiner1 = new StringJoiner(",","<",">");
		joiner1.add("h");
		joiner1.add("3");
		joiner1.add("9");
		System.out.println(joiner1.toString());
		
		
		List<String> list = Arrays.asList("java", "python", "nodejs", "ruby");
		System.out.println(String.join(",", list));
		
		
		List<String> list1 = Arrays.asList("java", "python", "nodejs", "ruby");
		System.out.println(list1.stream().map(x -> x).collect(Collectors.joining(",","(",")")));
	}
}
