/*
 * @Project Name: springmvcdemo
 * @File Name: TargetProxy.java
 * @Package Name: com.test.learn.jdk7.proxy
 * @Date: 2018年5月9日下午12:14:56
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import clojure.lang.Obj;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月9日下午12:14:56
 * @see
 */
public class TargetProxy implements InvocationHandler {

	private Object target;

	private Interceptor interceptor;


	public TargetProxy(Object target,Interceptor interceptor) {
		this.target = target;
		this.interceptor = interceptor;
	}

	public static Object bind(Object target,Interceptor interceptor){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),new TargetProxy(target, interceptor)
				);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//执行拦截
		return interceptor.intercept(new Invocation(target, method, args));

	}
}
