/*
 * @Project Name: springmvcdemo
 * @File Name: Interceptor.java
 * @Package Name: com.test.learn.jdk7.proxy
 * @Date: 2018年5月9日下午12:15:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7.proxy;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月9日下午12:15:50
 * @see
 */
public interface Interceptor {
	
	    public Object intercept(Invocation invocation) throws Exception;
	    
	    public Object register(Object target);
}

