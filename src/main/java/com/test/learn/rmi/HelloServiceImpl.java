/*
 * @Project Name: springmvcdemo
 * @File Name: HelloServiceImpl.java
 * @Package Name: com.test.learn.rmi
 * @Date: 2018年1月22日上午9:53:51
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月22日上午9:53:51
 * @see
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{

	protected HelloServiceImpl() throws RemoteException {
	}

	/**
	 * serialVersionUID: TODO
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String sayHello(String name) throws RemoteException {

		return "hello " + name;
	}
}
