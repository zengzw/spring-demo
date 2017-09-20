/*
 * @Project Name: springmvcdemo
 * @File Name: Proxy.java
 * @Package Name: com.test.learn.proxy.chain
 * @Date: 2017年9月19日下午3:15:02
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxy.chain;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月19日下午3:15:02
 * @see
 */
public interface Proxy {
	
	void doProxy(ProxyChain proxyChain);
}
