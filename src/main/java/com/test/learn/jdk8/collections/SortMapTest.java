/*
 * @Project Name: springmvcdemo
 * @File Name: SortMapTest.java
 * @Package Name: com.test.learn.jdk8.collections
 * @Date: 2018年1月30日下午5:36:53
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月30日下午5:36:53
 * @see
 */
public class SortMapTest {

	public static void main(String[] args) {
		Map<String, Integer> unsortMap = new HashMap<>();
		unsortMap.put("z", 10);
		unsortMap.put("b", 5);
		unsortMap.put("a", 6);
		unsortMap.put("c", 20);
		unsortMap.put("d", 1);
		unsortMap.put("e", 7);
		unsortMap.put("y", 8);
		unsortMap.put("n", 99);
		unsortMap.put("g", 50);
		unsortMap.put("m", 2);
		unsortMap.put("f", 9);

		System.out.println(unsortMap);


		//sort map key,and return new LinkHashMap
		
		//To Map 默认返回HashMap 对象
		Map<String, Integer> resultMap = unsortMap.entrySet().stream()
		.sorted(Map.Entry.comparingByKey())
		.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(oldValue,newValue) -> oldValue,LinkedHashMap::new)); 
		
		System.out.println(resultMap);
		
		
		//sort by value
		//(oldValue, newValue) -> oldValue ==> If the key is duplicated, do you prefer oldKey or newKey?
		Map<String, Integer> valueMap =  unsortMap.entrySet().stream()
		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(oldValue,newValue) -> oldValue,LinkedHashMap::new));
		System.out.println(valueMap);
		
				
		

	}
}
