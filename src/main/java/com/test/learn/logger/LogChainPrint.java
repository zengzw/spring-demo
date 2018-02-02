/*
 * @Project Name: springmvcdemo
 * @File Name: LogChainPrint.java
 * @Package Name: com.test.learn.logger
 * @Date: 2018年1月29日上午9:55:28
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.logger;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月29日上午9:55:28
 * @see
 */
public class LogChainPrint {
	
	public static ThreadLocal<LogChain> threadLocal = new ThreadLocal<>();
	
	public static void log(LogChain logChain){
		LogChain parentChain = threadLocal.get();
		if(parentChain == null){
			threadLocal.set(logChain);
		}
		System.out.println("---->:{}"+JSON.toJSONString(logChain));
	}
	
	public static LogChain get(){
		return threadLocal.get();
	}
}
