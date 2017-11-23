/*
 * @Project Name: springmvcdemo
 * @File Name: TransactionService.java
 * @Package Name: com.test.learn.proxytransaction
 * @Date: 2017年11月23日上午11:45:55
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxytransaction;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月23日上午11:45:55
 * @see
 */
public class TransactionService {
	

	/**
	 * 需要事务
	 * 
	 * @date 2017年11月23日下午12:28:39
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	@Transaction
	public void trainactionMethod(){
		System.out.println("----execute transaction method-----");
	}
	
	
	/**
	 * 没事务执行
	 * 
	 * @date 2017年11月23日下午12:28:47
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	public void method(){
		System.out.println("------ execute default method----");
	}
	
	
	
	
	public static void main(String[] args) {
		
		TransactionService service = TransactionProxy.getInstance().getProxy(TransactionService.class);
		service.trainactionMethod();
		
		System.out.println("*****************************");
		service.method();
		
	}
}
