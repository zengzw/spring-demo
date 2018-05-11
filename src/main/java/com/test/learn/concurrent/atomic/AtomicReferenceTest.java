/*
 * @Project Name: springmvcdemo
 * @File Name: AtomicReferenceTest.java
 * @Package Name: com.test.learn.concurrent.atomic
 * @Date: 2018年5月10日上午11:18:32
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 *  模板类，可以封装任意数据
 *  
 * @author zengzw-1220
 * @date 2018年5月10日上午11:18:32
 * @see
 */
public class AtomicReferenceTest {
	
	public static void main(String[] args) {

		 AtomicReference<String> atomicString = new AtomicReference("kktalk1");
		 
		 boolean trues = atomicString.compareAndSet("kktalk", "update");
		 System.err.println(trues);
		 
	}
}
