/*
 * @Project Name: springmvcdemo
 * @File Name: DistinctTest.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年4月17日下午2:51:37
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.alibaba.fastjson.JSON;
import com.test.learn.jdk8.vo.Hosting;

/**
 * 对象按照某个字段去重
 * 
 * @author zengzw-1220
 * @date 2018年4月17日下午2:51:37
 * @see
 */
public class DistinctTest {
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	
	public static void main(String[] args) {

		List<Hosting> list = new ArrayList<>();
		list.add(new Hosting(1, "liquidweb.com", 80000));
		list.add(new Hosting(2, "liquidweb.com", 90000));
		list.add(new Hosting(3, "liquidweb.com", 120000));
		list.add(new Hosting(4, "aws.amazon.com", 200000));
		list.add(new Hosting(5, "mkyong.com", 1));
		
		list.stream().filter(distinctByKey(Hosting::getName)).forEach(p->{
			System.out.println(JSON.toJSONString(p));
		});
	
		System.out.println("---------------");
		distinctObject();
	}
	
	
	public static void distinctObject(){

		List<Hosting> list = new ArrayList<>();
		list.add(new Hosting(1, "liquidweb.com", 80000));
		list.add(new Hosting(1, "liquidweb.com", 80000));
		list.add(new Hosting(3, "liquidweb.com", 120000));
		list.add(new Hosting(4, "aws.amazon.com", 200000));
		list.add(new Hosting(5, "mkyong.com", 1));
		
		list.stream().distinct().forEach(p->{
			System.out.println(JSON.toJSONString(p));
		});
	}
}
