/*
 * @Project Name: springmvcdemo
 * @File Name: flatMapTest.java
 * @Package Name: com.test.learn.jdk8.collections
 * @Date: 2018年1月31日上午11:22:47
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.collections;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import scala.collection.generic.BitOperations.Int;

/**
 * 将多个strem，合并到一个strem
 * @author zengzw-1220
 * @date 2018年1月31日上午11:22:47
 * @see
 */
public class flatMapTest {
	
	public static void main(String[] args) {

		  String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
		  Stream<String[]> stream = Arrays.stream(data);
		 /* stream.forEach(x ->{
			  Arrays.stream(x).forEach(System.out::println);
		  });*/
		  
	       //filter a stream of string[], and return a string[]?
	        Stream<String[]> stream1 = stream.filter(x -> "a".equals(x.toString()));
	        stream1.forEach(System.out::println);
	        
	        flatMap1();
	        
	        flatMap2();
	}
	
	public static void flatMap1(){
		System.out.println("------flatmap---------");
		  String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
		  Stream<String[]> stream = Arrays.stream(data);
		  
		  //得到 string 流，a,b,c,d,e,f
		  Stream<String> streamString = stream.flatMap(x->Arrays.stream(x));
		  
		  //对stream 流，进行过滤
		  Stream<String> resultStream = streamString.filter(x-> "a".equals(x.toString()));
//		  streamString.forEach(System.out::println);
		  resultStream.forEach(System.out::println);
	}
	
	
	/**
	 * 基本类型
	 * 
	 * @date 2018年1月31日下午2:22:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	public static void flatMap2(){
		System.out.println("--------flatmap2-----------");
		 int[] intArray = {1, 2, 3, 4, 5, 6};
		 Stream<int[]>  stream = Stream.of(intArray);
		 IntStream intStream = stream.flatMapToInt(x -> Arrays.stream(x));
		 intStream.forEach(x -> System.out.println(x));
		 
		 System.out.println("------------");
		 
		 IntStream intStream2 = Arrays.stream(intArray);
		 intStream2.forEach(System.out::println);
		 
	}
}
