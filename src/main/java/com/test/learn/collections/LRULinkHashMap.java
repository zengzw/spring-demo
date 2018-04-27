/*
 * @Project Name: springmvcdemo
 * @File Name: LRULinkHashMap.java
 * @Package Name: com.test.learn.collections
 * @Date: 2018年4月19日上午10:12:11
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  移除老的数据
 *  
 * @author zengzw-1220
 * @date 2018年4月19日上午10:12:11
 * @see
 */
public class LRULinkHashMap extends LinkedHashMap<String, String>{
	
	private static final int MAX_ENTRIES = 10;
	
	
	/**
	 * 当size > cache 的时候，会移除老的数据。
	 * false是，保持不变。 
	 *  
	 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
	 */
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {
		return (size() > MAX_ENTRIES);
	}
	
	
	public static void main(String[] args) throws InterruptedException {

		Map<String,String> hashMap = new LRULinkHashMap();
		for(int i = 0; i<15; i++){
			hashMap.put("key"+i, "value"+i);
			System.out.println(hashMap);
			Thread.sleep(800);
		}
		
		Thread.sleep(10 * 1000);
		System.out.println(hashMap);
	}
	
	
}

