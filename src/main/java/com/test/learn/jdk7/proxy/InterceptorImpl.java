/*
 * @Project Name: springmvcdemo
 * @File Name: InterceptorImpl.java
 * @Package Name: com.test.learn.jdk7.proxy
 * @Date: 2018年5月9日下午2:28:03
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
 * @date 2018年5月9日下午2:28:03
 * @see
 */
public class InterceptorImpl implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Exception{

		System.out.println("----intercept--go-go-go");
		return invocation.process();
	}

	@Override
	public Object register(Object target) {
		return TargetProxy.bind(target,this);
	}
}
