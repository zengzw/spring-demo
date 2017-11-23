/*
 * @Project Name: springmvcdemo
 * @File Name: ATest.java
 * @Package Name: com.test.springmvc
 * @Date: 2017年11月14日下午5:47:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月14日下午5:47:50
 * @see
 */
public class ATest {
	
	public void add(String a){
		a += "bb";
	}
	
	public static void main(String[] args) {

		ATest test = new ATest();
		String b = "aa";
		for(int i = 0; i<3; i++){
			test.add(b);
		}
		
		System.out.println(b);
	}
}
