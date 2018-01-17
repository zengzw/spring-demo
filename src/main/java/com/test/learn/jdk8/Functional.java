/*
 * @Project Name: springmvcdemo
 * @File Name: Functional.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年1月17日下午5:28:45
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

/**
 * 
 * 函数式接口只能包含一个抽象方法
函数式接口可以包含Object类中所有public修饰的方法
函数工接口可以包含一个或多个静态方或默认方法


 * @author zengzw-1220
 * @date 2018年1月17日下午5:28:45
 * @see
 */
@FunctionalInterface
public interface Functional {
	
	void method();
	
	
	default void method1(){
		System.out.println("默认实现");
	}
	
	

    // java.lang.Object中的方法不是抽象方法  
    public boolean equals(Object var1);  
  
    // default不是抽象方法  
    public default void defaultMethod(){  
  
    }  
  
    // static不是抽象方法  
    public static void staticMethod(){  
  
    }  

}
