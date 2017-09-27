/*
 * @Project Name: springmvcdemo
 * @File Name: ProjectManager.java
 * @Package Name: com.test.learn.disignpattern.chainofresponsibility
 * @Date: 2017年9月21日下午3:07:33
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disignpattern.chainofresponsibility;

/**
 * 项目经理
 * 
 * @author zengzw-1220
 * @date 2017年9月21日下午3:07:33
 * @see
 */
public class ProjectManager extends Handler{

	protected ProjectManager(int maxDay) {
		super(maxDay);
	}

	@Override
	protected void replay(int day) {

		System.out.println(day+"---我审批了");
		
	}
}
