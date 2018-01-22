/*
 * @Project Name: springmvcdemo
 * @File Name: Filterable.java
 * @Package Name: com.test.learn.jdk8.anotation
 * @Date: 2018年1月18日上午10:46:39
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.anotation;

import java.util.Arrays;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月18日上午10:46:39
 * @see
 */
@Filter1("hello")
@Filter1("hello1")
@Filter1("hello1")
@Filter1("hello1")
@Filter1("hello1")
@Filter1("hello1")
public interface Filterable {
	
	
	public static void main(String[] args) {

		Filter[] filters  = Filterable.class.getAnnotationsByType(Filter.class);
		for(Filter filter : filters){
			Arrays.asList(filter.value()).forEach((e) ->{
				System.out.println(e.value());
			});
		}
		
		Filter1[] filter1s = Filterable.class.getAnnotationsByType(Filter1.class);
		Arrays.asList(filter1s).forEach(e -> System.out.println(e.value()));
		
	}
}
