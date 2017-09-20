/*
 * @Project Name: springmvcdemo
 * @File Name: ProxyChain.java
 * @Package Name: com.test.learn.proxy.chain
 * @Date: 2017年9月19日下午3:14:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxy.chain;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月19日下午3:14:49
 * @see
 */
public class ProxyChain {
	
	private List<Proxy> proxyList;
	
	private int currentIndex = 0;
	
	private Class<?> targetClass;
	
	private Object targetObject;
	
	private Method targetMethod;
	
	private Object[] methodParams;
	
	private MethodProxy methodProxy;
	
	private Object methodResult;
	
	
	public ProxyChain(Class<?> targetClass,Object targetObject,Method targetMethod,Object[] methodParams,MethodProxy methodProxy, List<Proxy> proxyList) {
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodParams = methodParams;
		this.methodProxy = methodProxy;
		this.proxyList = proxyList;
	}

	public void doProxyChain(){
		if(currentIndex < proxyList.size()){
			proxyList.get(currentIndex++).doProxy(this);
		}else{
			System.out.println("-----");
			try {
				methodResult =  methodProxy.invokeSuper(targetObject, methodParams);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	
	public List<Proxy> getProxyList() {
	
		return proxyList;
	}


	
	public void setProxyList(List<Proxy> proxyList) {
	
		this.proxyList = proxyList;
	}


	
	public int getCurrentIndex() {
	
		return currentIndex;
	}


	
	public void setCurrentIndex(int currentIndex) {
	
		this.currentIndex = currentIndex;
	}


	
	public Class<?> getTargetClass() {
	
		return targetClass;
	}


	
	public void setTargetClass(Class<?> targetClass) {
	
		this.targetClass = targetClass;
	}


	
	public Object getTargetObject() {
	
		return targetObject;
	}


	
	public void setTargetObject(Object targetObject) {
	
		this.targetObject = targetObject;
	}


	
	public Method getTargetMethod() {
	
		return targetMethod;
	}


	
	public void setTargetMethod(Method targetMethod) {
	
		this.targetMethod = targetMethod;
	}


	
	public Object[] getMethodParams() {
	
		return methodParams;
	}


	
	public void setMethodParams(Object[] methodParams) {
	
		this.methodParams = methodParams;
	}


	
	public MethodProxy getMethodProxy() {
	
		return methodProxy;
	}


	
	public void setMethodProxy(MethodProxy methodProxy) {
	
		this.methodProxy = methodProxy;
	}


	
	public Object getMethodResult() {
	
		return methodResult;
	}


	
	public void setMethodResult(Object methodResult) {
	
		this.methodResult = methodResult;
	}
}
