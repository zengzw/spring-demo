/*
 * @Project Name: springmvcdemo
 * @File Name: UnixTime.java
 * @Package Name: com.test.learn.netty.testone.entity
 * @Date: 2017年9月28日下午4:38:05
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.netty.testone.entity;

import java.util.Date;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月28日下午4:38:05
 * @see
 */
public class UnixTime {
	private final long value;

	public UnixTime() {
		this(System.currentTimeMillis() / 1000L + 2208988800L);
	}

	public UnixTime(long value) {
		this.value = value;
	}

	public long value() {
		return value;
	}

	@Override
	public String toString() {
		return new Date((value() - 2208988800L) * 1000L).toString();
	}
}
