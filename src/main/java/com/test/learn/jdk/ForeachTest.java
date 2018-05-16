/*
 * @Project Name: springmvcdemo
 * @File Name: ForeachTest.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2018年5月14日下午2:35:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月14日下午2:35:50
 * @see
 */
public class ForeachTest {

	public static void main(String[] args) {

		retry:
			for (;;) {
				System.out.println("----");

				for (;;) {
					System.out.println(">>>>");
					if (1==1)
						break retry;

					/*	if (1 !=2)
						continue retry;*/
					// else CAS failed due to workerCount change; retry inner loop
				}
			}

	System.out.println("---->end");
	}
}
