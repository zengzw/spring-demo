/*
 * @Project Name: springmvcdemo
 * @File Name: RMIServer.java
 * @Package Name: com.test.learn.rmi
 * @Date: 2018年1月22日上午9:55:36
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月22日上午9:55:36
 * @see
 */
public class RMIServer {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {

		int port = 8080;
		
		String uri = "rmi://localhost:"+port +"/demo.rim.server.HelloServiceImpl";
		
		LocateRegistry.createRegistry(port);
		
		Naming.rebind(uri,new HelloServiceImpl());
	}
}
