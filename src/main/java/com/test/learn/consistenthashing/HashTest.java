/*
 * @Project Name: springmvcdemo
 * @File Name: HashTest.java
 * @Package Name: com.test.learn.consistenthashing
 * @Date: 2018年5月22日下午2:44:55
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.consistenthashing;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月22日下午2:44:55
 * @see
 */
public class HashTest {
	
	public static void main(String[] args) {

		//10个桶
		int bucket = 10;
		
		for(int i = 0; i<50;i++){
			
			int index = (""+i).hashCode() % bucket;
			System.out.println(i+" save index:"+index);
		}
		
	System.out.println("----------------");
		for(int i = 0; i<50;i++){
			
			int index = (""+i).hashCode() % 11;
			System.out.println(i+" save index:"+index);
		}
	}
}
