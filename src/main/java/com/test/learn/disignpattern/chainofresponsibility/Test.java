/*
 * @Project Name: springmvcdemo
 * @File Name: Test.java
 * @Package Name: com.test.learn.disignpattern.chainofresponsibility
 * @Date: 2017年9月21日下午3:10:24
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
 * @date 2017年9月21日下午3:10:24
 * @see
 */
public class Test {
	
	public static void main(String[] args) {
		Handler projectManager = new ProjectManager(3);
		Handler departmentManager = new DepartmentManager(5);
		Handler generalManager = new GeneralManager(15);
		
		projectManager.setNextHadler(departmentManager);
		departmentManager.setNextHadler(generalManager);
		
		
		projectManager.hadleRequest(4);
		
		
	}
}
