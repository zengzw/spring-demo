/*
 * @Project Name: springmvcdemo
 * @File Name: Task.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年1月18日下午4:08:13
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.stream;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月18日下午4:08:13
 * @see
 */
public class Task {
	private  Integer status;
	private  Integer points;

	Task( final Integer status, final Integer points ) {
		this.status = status;
		this.points = points;
	}

	public Integer getPoints() {
		return points;
	}

	public Integer getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return String.format( "[%s, %d]", status, points );
	}
}
