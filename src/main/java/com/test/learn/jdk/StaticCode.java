/*
 * @Project Name: springmvcdemo
 * @File Name: classStaticCode.java
 * @Package Name: com.test.learn.jdk
 * @Date: 2018年5月15日上午10:48:21
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
 * @date 2018年5月15日上午10:48:21
 * @see
 */
public class StaticCode {  
	int num=9;
	StaticCode(){
		System.out.println("---construct---");
		System.out.println("b");
	}

	//静态代码块
	static{
		System.out.println("--static{}---");
		System.out.println("a");
	}

	//构造代码块
	{
		System.out.println("-----{}----");
		System.out.println("c");
	}

	StaticCode(int x){
		System.out.println("d");
	}
	
	public static void main(String[] args) {

	    new StaticCode();
	    
	    
	    System.out.println("a".hashCode());
	    System.out.println("b".hashCode());
	    System.out.println("c".hashCode());
	}
}