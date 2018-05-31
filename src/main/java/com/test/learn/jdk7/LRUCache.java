/*
 * @Project Name: springmvcdemo
 * @File Name: LRUCache.java
 * @Package Name: com.test.learn.jdk7
 * @Date: 2018年5月30日下午12:10:32
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用LinkhashMap 实现LRU cache
 * 
 * @author zengzw-1220
 * @date 2018年5月30日下午12:10:32
 * @see
 */
public class LRUCache<K,V> extends LinkedHashMap<K, V>{
	
	/**
	 * serialVersionUID: TODO
	 */
	private static final long serialVersionUID = -4932053473556753978L;
	
	
	private   final int MAX_CACHE_SIZE;
	
	public LRUCache(int cacheSize) {
		super(cacheSize, 0.75F, true);
		MAX_CACHE_SIZE = cacheSize;
	}
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
	
		return size() > MAX_CACHE_SIZE;
	}
	
	public static void main(String[] args) {

		Map<String, Object> cache = new LRUCache<>(10);
		for(int i = 0 ; i< 22; i++){
			cache.put(i+"", i+"value");
			System.out.println(cache);
		}
		
	}
}

