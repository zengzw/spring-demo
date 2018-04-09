/*
 * @Project Name: springmvcdemo
 * @File Name: Student.java
 * @Package Name: com.test.learn.collections
 * @Date: 2018年3月21日上午10:55:16
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.collections;

import java.util.Date;

/**
 * 
 * 
 * @author zengzw-1220
 * @date 2018年3月21日上午10:55:16
 * @see
 */
public class Student {
	
	private int status;
	
	private Date modifyTime;
	
	private String name;
	
	
	
	public int getStatus() {
	
		return status;
	}


	
	public void setStatus(int status) {
	
		this.status = status;
	}


	
	public Date getModifyTime() {
	
		return modifyTime;
	}


	
	public void setModifyTime(Date modifyTime) {
	
		this.modifyTime = modifyTime;
	}


	
	public String getName() {
	
		return name;
	}


	
	public void setName(String name) {
	
		this.name = name;
	}


	public Student(int status,Date time,String name){
		this.status = status;
		this.modifyTime = time;
		this.name = name;
	}
}


