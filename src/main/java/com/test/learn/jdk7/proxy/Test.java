/*
 * @Project Name: springmvcdemo
 * @File Name: Test.java
 * @Package Name: com.test.learn.jdk7.proxy
 * @Date: 2018年5月9日下午12:32:23
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7.proxy;

/**
 * https://www.tuicool.com/articles/RbyUfu
 * 
 * @author zengzw-1220
 * @date 2018年5月9日下午12:32:23
 * @see
 */
public class Test {
	public static void main(String[] args) {
		//客户端可以定义各种拦截逻辑
		Interceptor interceptor = new InterceptorImpl();
		
		 Target target = (Target) interceptor.register(new TargetImpl());
		 
		 target.execute();
	}
}
