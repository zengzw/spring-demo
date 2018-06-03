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
public class StaticCodeParent {  
	int num=9;
	//构造方法 3
	StaticCodeParent(){
		System.out.println("parent ---construct---");
		System.out.println(" parent b");
	}

	//静态代码块  1
	static{
		System.out.println("parent--static{}---");
		System.out.println("parent a");
	}

	//构造代码块 2
	{
		System.out.println("parent-----{}----");
		System.out.println(" parent c");
	}

	StaticCodeParent(int x){
		System.out.println("d");
	}
	
	public static void main(String[] args) {

	    new StaticCodeParent();
	    
	    
	    System.out.println("a".hashCode());
	    System.out.println("b".hashCode());
	    System.out.println("c".hashCode());
	}
}