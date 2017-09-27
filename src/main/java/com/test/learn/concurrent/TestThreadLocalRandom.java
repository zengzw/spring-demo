/*
 * @Project Name: springmvcdemo
 * @File Name: TestThreadLocalRandom.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2017年9月22日上午10:34:46
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月22日上午10:34:46
 * @see
 */
public class TestThreadLocalRandom {
	
	public static void main(String[] args) {

		ThreadLocalRandom random = ThreadLocalRandom.current();
		System.out.println(random.nextInt(20));
	}
}
