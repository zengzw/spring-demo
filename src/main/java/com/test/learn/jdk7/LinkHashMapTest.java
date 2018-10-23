/*
 * @Project Name: springmvcdemo
 * @File Name: LinkHashMapTest.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2018年4月28日下午2:26:53
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7;

import java.util.LinkedHashMap;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月28日下午2:26:53
 * @see
 */
public class LinkHashMapTest {
	
	public static void main(String[] args) {

		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("a", "a value");
		linkedHashMap.put("b", "b value");
		linkedHashMap.put("c", "c value");
		
		System.out.println(linkedHashMap);
		
		
		//根据访问来排序。访问的，放到后面
		LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(16,0.75F,true);
		linkedHashMap2.put("a", "a value");
		linkedHashMap2.put("b", "b value");
		linkedHashMap2.put("c", "c value");
		
		linkedHashMap2.get("a");
		linkedHashMap2.get("a");
		linkedHashMap2.get("a");
		
		System.out.println(linkedHashMap2);
	}
}
