/*
 * @Project Name: springmvcdemo
 * @File Name: DefaulableFactory.java
 * @Package Name: com.test.learn.jdk8.interfaces
 * @Date: 2018年1月18日上午10:03:35
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.interfaces;

import java.util.function.Supplier;

/** 
 * @author zengzw-1220
 * @date 2018年1月18日上午10:03:35
 * @see
 */
public interface DefaulableFactory {

	static Defaulable create(Supplier<Defaulable> supplier){
		return supplier.get();
	}
}
