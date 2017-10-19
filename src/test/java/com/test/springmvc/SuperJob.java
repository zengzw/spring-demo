/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 *
 * @author zengzw
 * @date 2017年5月22日
 */
public class SuperJob {
	
	public static void main(String[] args) {

		Map<String, Object> cMap = new HashMap<>();
		cMap.put("篮球","1");
		cMap.put("手机","1");
		Map<String, Object> maps = new HashMap<>();
		maps.put("plan", cMap);
		
		Map<String, Object> cMap1 = new HashMap<>();
		cMap1.put("口哨","1");
		cMap1.put("足球","1");
		
		maps.put("器材", cMap1);
		
		
		System.out.println(JSON.toJSONString(maps));
	}

}
