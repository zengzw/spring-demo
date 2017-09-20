/*
 * @Project Name: springmvcdemo
 * @File Name: RpcResponse.java
 * @Package Name: com.test.learn.rpc.vo
 * @Date: 2017年9月2日上午9:31:42
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.vo;

/**
 *  
 * @author zengzw-1220
 * @date 2017年9月2日上午9:31:42
 * @see
 */
public class RpcResponse {

	/**
	 * 响应ID
	 */
    private String requestId;
    
    /**
     * 错误信息
     */
    private Throwable error;
    
    /**
     * 结果对象
     */
    private Object result;

	
	public String getRequestId() {
		return requestId;
	}

	
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	
	public Throwable getError() {
		return error;
	}

	
	public void setError(Throwable error) {
		this.error = error;
	}

	
	public Object getResult() {
		return result;
	}

	
	public void setResult(Object result) {
		this.result = result;
	}

}
