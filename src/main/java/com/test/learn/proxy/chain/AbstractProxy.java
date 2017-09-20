/*
 * @Project Name: springmvcdemo
 * @File Name: AbstractProxy.java
 * @Package Name: com.test.learn.proxy.chain
 * @Date: 2017年9月19日下午4:35:39
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxy.chain;

import java.lang.reflect.Method;

/**
 * 
 * @author zengzw-1220
 * @date 2017年9月19日下午4:35:39
 * @see
 */
public class AbstractProxy implements Proxy{

	@Override
	public void doProxy(ProxyChain proxyChain) {

		Class<?> cls = proxyChain.getTargetClass();
		Method method = proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();		

		try{
			if(filter(cls, method, params)){
				before(cls, method, params);

				proxyChain.doProxyChain();

				after(cls, method, params);
			}else{
				proxyChain.doProxyChain();
			}
		}catch(Exception e){
			error(cls, method, params, e);
		}finally {
			end();
		}

	}


	public void begin() {
	}

	public boolean filter(Class<?> cls, Method method, Object[] params) {
		return true;
	}

	public void before(Class<?> cls, Method method, Object[] params) {
	}

	public void after(Class<?> cls, Method method, Object[] params) {
	}

	public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
	}

	public void end() {
	}
}
