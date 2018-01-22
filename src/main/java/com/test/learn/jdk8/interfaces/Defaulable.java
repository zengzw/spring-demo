/*
 * @Project Name: springmvcdemo
 * @File Name: Defaulable.java
 * @Package Name: com.test.learn.jdk8.interfaces
 * @Date: 2018年1月18日上午10:00:54
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.interfaces;

import clojure.test__init;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月18日上午10:00:54
 * @see
 */
public interface Defaulable {
	
	default String noRequired(){
		return "Default implemention";
	}
	
	default String test(){
		return "test";
	}
}
