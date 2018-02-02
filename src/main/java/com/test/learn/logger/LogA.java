/*
 * @Project Name: springmvcdemo
 * @File Name: LogA.java
 * @Package Name: com.test.learn.logger
 * @Date: 2018年1月29日上午10:17:44
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.logger;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月29日上午10:17:44
 * @see
 */
public class LogA {

	public void cai(){
		System.out.println("采茶");
		
		LogChain logChain = LogChainPrint.get();
		if(logChain != null){
			String spandId = logChain.getSpanId();
			logChain.setMethod("cai");
			logChain.setSpanId(spandId+".1");
			LogChainPrint.log(logChain);
		}
	}

	public void yun(){
		System.out.println("运茶");
		LogChain logChain = LogChainPrint.get();
		if(logChain != null){
			String spandId = logChain.getSpanId();
			String before = spandId.substring(0, spandId.lastIndexOf(".")+1);
			spandId = spandId.substring(spandId.lastIndexOf(".")+1);
			if(spandId != null){
				spandId = before + (Integer.parseInt(spandId)+1);
			}
			
			logChain.setMethod("yun");
			logChain.setSpanId(spandId);
			LogChainPrint.log(logChain);
		}
	}

	public void sai(){
		System.out.println("晒茶");
		
		LogChain logChain = LogChainPrint.get();
		if(logChain != null){
			String spandId = logChain.getSpanId();
			logChain.setMethod("sai");
			String before = spandId.substring(0, spandId.lastIndexOf(".")+1);
			spandId = spandId.substring(spandId.lastIndexOf(".")+1);
			if(spandId != null){
				spandId = before + (Integer.parseInt(spandId)+1);
			}
			logChain.setSpanId(spandId);
			LogChainPrint.log(logChain);
		}
	}

	public void make(){
		LogChain logChain = new LogChain();
		logChain.setTraceId(123456);
		logChain.setMethod("make");
		logChain.setSpanId("0");
		LogChainPrint.log(logChain);
		this.cai();

		this.yun();

		this.sai();
	}
	
	public static void main(String[] args) {

		LogA logA = new LogA();
		logA.make();
	}
}
