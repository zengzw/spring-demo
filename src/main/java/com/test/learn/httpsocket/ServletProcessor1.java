/*
 * @Project Name: springmvcdemo
 * @File Name: ServletProcessor1.java
 * @Package Name: com.test.learn.httpsocket
 * @Date: 2017年11月22日上午11:11:06
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.httpsocket;

import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Servlet;

import kafka.api.Request;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月22日上午11:11:06
 * @see
 */
public class ServletProcessor1 {

	public void process(Request request,Response response){
	/*	URL[] urls = new URL[1];
		URLClassLoader loader  = new URLClassLoader(urls);

		Class mClass;
		try {
			mClass = loader.loadClass("serlvetName");
			try {
				Servlet servlet = (Servlet)mClass.newInstance();
				servlet.service(request, response);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
}
