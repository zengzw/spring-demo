/*
 * @Project Name: springmvcdemo
 * @File Name: HelloService.java
 * @Package Name: com.test.learn.rmi
 * @Date: 2018年1月22日上午9:52:33
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月22日上午9:52:33
 * @see
 */
public interface HelloService extends Remote{
	
	
	String sayHello(String name) throws RemoteException;
}
