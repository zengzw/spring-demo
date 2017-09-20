/*
 * @Project Name: springmvcdemo
 * @File Name: TestClient.java
 * @Package Name: com.test.learn.proxy.chain.impl
 * @Date: 2017年9月19日下午4:44:18
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxy.chain.impl;

import java.util.ArrayList;
import java.util.List;

import com.test.learn.proxy.chain.Proxy;
import com.test.learn.proxy.chain.ProxyManager;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月19日下午4:44:18
 * @see
 */
public class TestClient {
	
	public static void main(String[] args) {

		List<Proxy> proxyList = new ArrayList<>();
		proxyList.add(new BeforeProxy());
		proxyList.add(new AfterProxy());
		
		ProxyManager manager = new ProxyManager(GreetingImpl.class, proxyList);
		GreetingImpl greetingImpl = manager.createProxy();
		greetingImpl.sayHello("phoenix");
		
	}
}
