/*
 * @Project Name: springmvcdemo
 * @File Name: RPCCustomer.java
 * @Package Name: com.test.learn.rpc.simple
 * @Date: 2018年3月12日上午10:18:37
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.simple;

import com.test.learn.rpc.simple.service.HelloService;
import com.test.learn.rpc.simple.service.StudyService;
import com.test.learn.xml.xstream.bean.Student;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月12日上午10:18:37
 * @see
 */
public class RPCCustomer {

	public static void main(String[] args) {

		HelloService helloService = RPCManager.refer(HelloService.class, "127.0.0.1", 8080);
		String hello = helloService.hello("RPC");

		System.out.println(hello);
		
		StudyService studyService = RPCManager.refer(StudyService.class,"127.0.0.1", 8080);
		String studyMsg = studyService.study("people");
		System.out.println(studyMsg);

	}
}
