/*
 * @Project Name: springmvcdemo
 * @File Name: LogChain.java
 * @Package Name: com.test.learn.logger
 * @Date: 2018年1月29日上午9:52:06
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
 * @date 2018年1月29日上午9:52:06
 * @see
 */
public class LogChain {
	
	private int traceId;
	
	private String spanId;
	
	private String method;
	
	private Object paramts;

	
	public int getTraceId() {
	
		return traceId;
	}

	
	public void setTraceId(int traceId) {
	
		this.traceId = traceId;
	}

	
	public String getSpanId() {
	
		return spanId;
	}

	
	public void setSpanId(String spanId) {
	
		this.spanId = spanId;
	}

	
	public String getMethod() {
	
		return method;
	}

	
	public void setMethod(String method) {
	
		this.method = method;
	}

	
	public Object getParamts() {
	
		return paramts;
	}

	
	public void setParamts(Object paramts) {
	
		this.paramts = paramts;
	}
}
