/*
 * @Project Name: springmvcdemo
 * @File Name: JDK8Test.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年1月17日下午3:36:51
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.util.Arrays;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月17日下午3:36:51
 * @see
 */
public class JDK8Test {
	
	public static void main(String[] args) {

		Arrays.asList("a","d","c").forEach(e -> System.out.println(e));
		
		System.out.println("------------");
		
		Arrays.asList("a","b","c").forEach(
			(String e) -> System.out.println(e));
		
		System.out.println("------------");
		
		Arrays.asList("a","b","c").forEach(e -> {
			System.out.println(e);
		});
		
		System.out.println("-----------");
		
		Arrays.asList("a","d","c").sort((a,e) -> {
			System.out.println("p1 : "+a +" p2: "+e);
			return a.compareTo(e);
		});
		
	}
}
