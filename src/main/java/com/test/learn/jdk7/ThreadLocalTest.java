/*
 * @Project Name: springmvcdemo
 * @File Name: ThreadLocalTest.java
 * @Package Name: com.test.learn.jdk7
 * @Date: 2018年5月3日下午6:02:15
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月3日下午6:02:15
 * @see
 */
public class ThreadLocalTest {

	public static void main(String[] args) {

		ThreadLocal<String> threadLocal = new ThreadLocal(){
			@Override
			protected Object initialValue() {

				return super.initialValue();
			}
		};

		threadLocal.set("--ak47");


		System.out.println(ThreadLocalTest.class.getClassLoader());

		Integer a = new Integer(5);
		Integer b = new Integer(5);

		System.out.println(a == b);



		final String str1 = "a";
		String str2 = "ab";
		String str3 = str1 + "b";
		System.out.println(str2 == str3);
		String str4 = "abc".substring(0,2);
		System.out.println(str4 == str2);




	}


	public void  swap(StringBuffer a,StringBuffer b){
		a.append(b);
	}
}
