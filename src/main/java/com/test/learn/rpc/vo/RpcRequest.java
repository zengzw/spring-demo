/*
 * @Project Name: springmvcdemo
 * @File Name: RpcRequest.java
 * @Package Name: com.test.learn.rpc.vo
 * @Date: 2017年9月2日上午9:31:35
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.vo;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月2日上午9:31:35
 * @see
 */
public class RpcRequest {
	/**
	 * 轻轻ID
	 */
	private String requestId;
	
	/**
	 * 类名称
	 */
	private String className;
	
	/**
	 * 方法名
	 */
	private String methodName;
	
	/**
	 * 参数类型
	 */
	private Class<?>[] parameterTypes;
	
	/**
	 * 参数
	 */
	private Object[] parameters;

	
	public String getRequestId() {
		return requestId;
	}

	
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	
	public String getClassName() {
		return className;
	}

	
	public void setClassName(String className) {
		this.className = className;
	}

	
	public String getMethodName() {
		return methodName;
	}

	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	
	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	
	public Object[] getParameters() {
		return parameters;
	}

	
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
