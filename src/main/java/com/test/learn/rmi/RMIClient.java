/*
 * @Project Name: springmvcdemo
 * @File Name: RMIClient.java
 * @Package Name: com.test.learn.rmi
 * @Date: 2018年1月22日上午9:58:46
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月22日上午9:58:46
 * @see
 */
public class RMIClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		int port = 8080;
		String uri = "rmi://localhost:"+port +"/demo.rim.server.HelloServiceImpl";

		HelloService helloService = (HelloService)Naming.lookup(uri);
		String result = helloService.sayHello("tea");
		System.out.println(result);
		
	}
}
