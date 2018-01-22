/*
 * @Project Name: springmvcdemo
 * @File Name: StreamTest2.java
 * @Package Name: com.test.learn.jdk8.stream
 * @Date: 2018年1月19日上午9:57:45
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.stream;

import java.util.stream.Stream;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月19日上午9:57:45
 * @see
 */
public class StreamTest2 {
	
	public static void main(String[] args) {

		Task[] tasks = {new Task(1, 10),new Task(2, 30),new Task(3, 40)};
		
		Stream<Task> stream = Stream.of(tasks);
		
		int a = 0;
		stream.forEach((t) ->{
			System.out.println(t.getStatus());
		});
	
		
		int sum = stream.mapToInt(t -> t.getPoints()).sum();
		System.out.println(sum);
	}
	
	
	
	
}
