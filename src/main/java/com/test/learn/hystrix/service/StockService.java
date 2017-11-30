/*
 * @Project Name: springmvcdemo
 * @File Name: StockService.java
 * @Package Name: com.test.learn.hystrix.service
 * @Date: 2017年11月29日下午2:19:47
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.hystrix.service;

/**
 * 
 * @author zengzw-1220
 * @date 2017年11月29日下午2:19:47
 * @see
 */
public class StockService {
	
	
	public String getStock(){
		System.out.println("---get Stock----");
		return "{success:true,stock:[id:1,name:stock]}";
	}
	
	
	public boolean setStock(int i){
		System.out.println("----set stock success:"+i);
		return true;
	}
	
}
