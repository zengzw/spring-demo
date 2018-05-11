/*
 * @Project Name: springmvcdemo
 * @File Name: MapVO.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2018年4月27日下午2:51:34
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月27日下午2:51:34
 * @see
 */
public class MapVO {
	
	private int id;
	
	private String name;
	
	public int getId() {
	
		return id;
	}
	
	public void setId(int id) {
	
		this.id = id;
	}
	
	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<>();
		map.put("", "value");
		
		MapVO mapVO = new MapVO();
		mapVO.setName("kk");
		MapVO[] mapVOs = new MapVO[10];
		mapVOs[0] = mapVO; 
		
		for(MapVO e = mapVOs[0]; e!= null; e.name.equals("kk")){
			System.out.println("----");
		}
	}
}
