/*
 * @Project Name: springmvcdemo
 * @File Name: GeneralManager.java
 * @Package Name: com.test.learn.disignpattern.chainofresponsibility
 * @Date: 2017年9月21日下午3:09:37
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
 * @date 2017年9月21日下午3:09:37
 * @see
 */
public class GeneralManager extends Handler{

	protected GeneralManager(int maxDay) {
		super(maxDay);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void replay(int day) {
		System.out.println(day+" GeneralManager 审批");

	}
}
