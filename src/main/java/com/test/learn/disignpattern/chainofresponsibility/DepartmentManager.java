/*
 * @Project Name: springmvcdemo
 * @File Name: DepartmentManager.java
 * @Package Name: com.test.learn.disignpattern.chainofresponsibility
 * @Date: 2017年9月21日下午3:08:48
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disignpattern.chainofresponsibility;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月21日下午3:08:48
 * @see
 */
public class DepartmentManager extends Handler{

	protected DepartmentManager(int maxDay) {
		super(maxDay);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void replay(int day) {

	System.out.println(day+ "DepartmentManager 审批");
		
	}
}
