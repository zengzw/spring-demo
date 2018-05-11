/*
 * @Project Name: springmvcdemo
 * @File Name: Invocation.java
 * @Package Name: com.test.learn.jdk7.proxy
 * @Date: 2018年5月9日下午2:24:35
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月9日下午2:24:35
 * @see
 */
public class Invocation {
	
	private Object target;
	
	private Method method;
	
	private Object[] args;
	
	
	public Invocation(Object target,Method method,Object[] args){
		this.target = target;
		this.method = method;
		this.args = args;
	}
	
	
	public Object process() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return method.invoke(target, args);
	}


	
	public Object getTarget() {
	
		return target;
	}


	
	public void setTarget(Object target) {
	
		this.target = target;
	}


	
	public Method getMethod() {
	
		return method;
	}


	
	public void setMethod(Method method) {
	
		this.method = method;
	}


	
	public Object[] getArgs() {
	
		return args;
	}


	
	public void setArgs(Object[] args) {
	
		this.args = args;
	}
	
	
	
}
