/*
 * @Project Name: springmvcdemo
 * @File Name: UUIDTest.java
 * @Package Name: com.test.learn.distribute.generateid
 * @Date: 2018年5月22日上午11:43:33
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.distribute.generateid;

import java.util.UUID;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月22日上午11:43:33
 * @see
 */
public class UUIDTest {
	
	public static void main(String[] args) {
		
		for(int i = 0; i<100; i++){
			System.out.println(UUID.randomUUID());
		}
	}
}
