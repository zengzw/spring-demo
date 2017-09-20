/*
 * @Project Name: springmvcdemo
 * @File Name: ProxyManager.java
 * @Package Name: com.test.learn.proxy.chain
 * @Date: 2017年9月19日下午4:10:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxy.chain;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月19日下午4:10:50
 * @see
 */
public class ProxyManager {
	
	private Class<?> targetClass;
	
	private List<Proxy> proxyList;
	
	
	public ProxyManager(Class<?> targetClass,List<Proxy> proxyList) {
		this.targetClass = targetClass;
		this.proxyList = proxyList;
	}
	
	
	public <T> T createProxy(){
		T create = (T)Enhancer.create(targetClass, new MethodInterceptor(){

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

				ProxyChain proxyChain = new ProxyChain(targetClass, obj, method, args, proxy, proxyList);
				proxyChain.doProxyChain();
				return proxyChain.getMethodResult();
			}
			
		});
		
		return create;
	}
}


