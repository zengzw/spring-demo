/*
 * @Project Name: springmvcdemo
 * @File Name: ClassUtilsTest.java
 * @Package Name: com.test.learn.classutils
 * @Date: 2017年11月24日下午3:11:36
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.classutils;

import java.io.IOException;
import java.util.List;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月24日下午3:11:36
 * @see
 */
public class ClassUtilsTest {
	public static void main(String[] args) throws IOException {
		String packageName = "java.util";
		List<Class<?>> listClass = new ClassTemplate(packageName){
			@Override
			public boolean checkAddClass(Class<?> clz) {

				return true;
			}
		}.getListClass();
		
		
		for(Class<?> clz : listClass){
			System.out.println(clz.getName());
		}
	}
}
