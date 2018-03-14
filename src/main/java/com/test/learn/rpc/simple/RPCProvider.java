/*
 * @Project Name: springmvcdemo
 * @File Name: RPCProvider.java
 * @Package Name: com.test.learn.rpc.simple
 * @Date: 2018年3月12日上午10:14:54
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.test.learn.rpc.simple.service.HelloService;
import com.test.learn.rpc.simple.service.HelloServiceImpl;
import com.test.learn.rpc.simple.service.StudyService;
import com.test.learn.rpc.simple.service.StudyServiceImpl;

/**
 * RPC 提供者
 * 
 * @author zengzw-1220
 * @date 2018年3月12日上午10:14:54
 * @see
 */
public class RPCProvider {
	
	public static void main(String[] args) throws IOException {
		HelloService helloService = new HelloServiceImpl();
		StudyService studyService = new StudyServiceImpl();
		
		List<Object> list = new ArrayList<>();
		list.add(helloService);
		list.add(studyService);
		
		//绑定服务
		RPCManager.initBind(list);
		
		//启动服务
		RPCManager.startService(8080);
	}
}
