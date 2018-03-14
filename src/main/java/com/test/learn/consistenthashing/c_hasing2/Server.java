/*
 * @Project Name: springmvcdemo
 * @File Name: Server.java
 * @Package Name: com.test.learn.consistenthashing
 * @Date: 2018年3月14日上午10:41:33
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.consistenthashing.c_hasing2;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器
 * 
 * @author zengzw-1220
 * @date 2018年3月14日上午10:41:33
 * @see
 */
public class Server {
	
	private String name;
	
	private Map<Entry, Entry> entries;
	
	public Server(String name) {
		this.name = name;
		entries = new HashMap<>();
	}
	
	
	public void put(Entry entry){
		entries.put(entry, entry);
	}
	
	public Entry get(Entry entry){
		return  entries.get(entry);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	
}
