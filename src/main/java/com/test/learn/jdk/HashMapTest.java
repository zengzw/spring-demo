/*
 * @Project Name: springmvcdemo
 * @File Name: HashMapTest.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2018年4月27日下午2:50:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月27日下午2:50:49
 * @see
 */
public class HashMapTest {
	
	public static void main(String[] args) {
		/*MapVO mapVO = new MapVO();
		mapVO.setId(1);
		
		HashMap<String, MapVO> map = new HashMap<>();
		
		map.put("aa", mapVO);
		
		
		MapVO mapVO1 = new MapVO();
		mapVO1.setId(2);
		map.put("aa", mapVO1);
		
		System.out.println(map);*/
		
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 1);
		map.put("数学", 2);
		map.put("英语", 3);
		map.put("历史", 4);
		map.put("政治", 5);
		map.put("地理", 6);
		map.put("生物", 7);
		map.put("化学", 8);
		for(Entry<String, Integer> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		System.out.println("数学".hashCode());
		System.out.println("化学".hashCode());
	}
}
