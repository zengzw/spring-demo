/*
 * @Project Name: springmvcdemo
 * @File Name: StreamTest.java
 * @Package Name: com.test.learn.jdk8.stream
 * @Date: 2018年1月18日下午4:09:00
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月18日下午4:09:00
 * @see
 */
public class StreamTest {
	static final Collection< Task > tasks = Arrays.asList(
			new Task( 1, 5 ),
			new Task(2, 13 ),
			new Task( 1, 8 ) 
			);
	
	public static void main(String[] args) {
		
		//过滤status = 1的 points 总和
		int resut = tasks.stream()
		.filter(task -> task.getStatus().intValue() == 1)
		.mapToInt(Task::getPoints)
		.sum();
		System.out.println(resut);
		
		//parallel processing
		
		final double totalPoints = tasks.stream()
		.parallel()
		.map(task -> task.getPoints())
		.reduce(0, Integer::sum);
		
		System.out.println(totalPoints);
		
		
		//// Group tasks by their status
		
		Map<Integer, List<Task>> mapResult = tasks.stream()
		.collect(Collectors.groupingBy(Task::getStatus));
		
		System.out.println(mapResult);
		
		
	}
}
