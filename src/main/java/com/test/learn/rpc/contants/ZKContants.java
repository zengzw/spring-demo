/*
 * @Project Name: springmvcdemo
 * @File Name: ZKContants.java
 * @Package Name: com.test.learn.rpc.contants
 * @Date: 2017年9月1日下午6:07:57
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.contants;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月1日下午6:07:57
 * @see
 */
public interface ZKContants {
	
	int ZK_SESSION_TIMEOUT = 5000;

	String ZK_REGISTRY_PATH = "/registry";
	
	String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}
