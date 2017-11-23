/*
 * @Project Name: springmvcdemo
 * @File Name: BaseAspect2.java
 * @Package Name: com.test.learn.proxyAOP
 * @Date: 2017年11月23日下午4:07:09
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxyAOP;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理链，看proxy 包下的例子
 * 
 * 
 * @author zengzw-1220
 * @date 2017年11月23日下午4:07:09
 * @see
 */
public class BaseAspect2 implements MethodInterceptor {

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}

	@Override
	public Object intercept(Object proxy, Method methodTarget, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object result = null;
		
		if (filter(methodTarget, args)) {
			
			before(methodTarget, args);
			
			try {
				
				result = methodProxy.invokeSuper(proxy, args);
				
			} catch (Exception e) {
				e.printStackTrace();
				error(methodTarget, args, e);
			}
			
			after(methodTarget, args);
			
		} else {
			
			result = methodProxy.invokeSuper(proxy, args);
		}
		
		return result;
	}

	protected boolean filter(Method method, Object[] args) {
		return true;
	}

	protected void before(Method method, Object[] args) {
	}

	protected void after(Method method, Object[] args) {
	}

	protected void error(Method method, Object[] args, Exception e) {
	}

}
