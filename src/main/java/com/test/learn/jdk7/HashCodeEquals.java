/*
 * @Project Name: springmvcdemo
 * @File Name: HashCodeEquals.java
 * @Package Name: com.test.learn.jdk7
 * @Date: 2018年5月4日上午9:06:24
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
 * @date 2018年5月4日上午9:06:24
 * @see
 */
public class HashCodeEquals {
	
	public static void main(String[] args) {

		HashCodeEntity a = new HashCodeEntity(1, "name");
		HashCodeEntity b = new HashCodeEntity(1, "name");
		
		System.out.println(a+" | "+b+"  " + a.hashCode() +" "+b.hashCode() );
		System.out.println("a equals b:"+ a.equals(b));
		System.out.println("a == b:"+( a==b));
		
		System.out.println("ee".hashCode());
		System.out.println("EE".hashCode());
		
		String str = "a";
		String strObj = new String("a");
		
		System.out.println(str.equals(strObj));
		System.out.println(str == strObj);
	}
}
