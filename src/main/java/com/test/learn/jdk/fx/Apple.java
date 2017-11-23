/*
 * @Project Name: springmvcdemo
 * @File Name: Apple.java
 * @Package Name: com.test.learn.jdk.fx
 * @Date: 2017年11月16日下午6:47:41
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk.fx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.test.springmvc.model.User;

import scala.language;
import scala.collection.generic.BitOperations.Int;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月16日下午6:47:41
 * @see
 */
public class Apple extends Fruit{
	 static List<Apple> apples = Arrays.asList(new Apple());
	 static List<User> others = Arrays.asList(new User());
	 static List<Fruit> fruits = Arrays.asList(new Fruit());
	 static List<String> strings = Arrays.asList(new String());
	 
	public static void main(String[] args) {
		Reat<Fruit> reat = new Reat<Fruit>();
		//reat.readE(fruits,new Apple());
		
		Apple apple = (Apple)getList(apples);
		
		Fruit fruit = getList(apples);
		
		Fruit fruit2 = getList(fruits);
		
		setSuperList(apples);
		setSuperList(fruits);
		setSuperList(strings);
		
		List<? super Fruit> l1 = new ArrayList();
		l1.add(new Fruit());
		l1.add(new Apple());
		
	}
	
	static class Reat<T>{
		void readE(List<? super T > list,T item){
			
			 list.add(item);
			 
		}
	}
	
	/**
	 * 
	 *  extend 子类 或者 包括本身
	 *  
	 *  可以通过Get 拿到数据。
	 *  不能set数据：
	 *  
	 *  例如：
	 *  Base -> A,B,C
	 *  set 进去的有可能是A，B、C，类型不能确定。
	 * 
	 */
	public static Fruit getList(List<? extends Fruit> list){
		return list.get(0);
	}
	
	
	public static <T> void setSuperList(List<? super T> list){
		System.out.println(list.get(0));
	}
		
}
