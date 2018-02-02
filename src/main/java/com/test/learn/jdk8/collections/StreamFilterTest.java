/*
 * @Project Name: springmvcdemo
 * @File Name: MapTest.java
 * @Package Name: com.test.learn.jdk8.collections
 * @Date: 2018年1月30日下午12:10:36
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.collections;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.test.learn.jdk8.vo.Staff;

import clojure.xml__init;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月30日下午12:10:36
 * @see
 */
public class StreamFilterTest {


	public static void main(String[] args) {


		testMapFilter();

		System.out.println("------");

		testMapFilter1();

		System.out.println("----find-any");
		testFindAny();
		
		System.out.println("----find-first---");
		
		testFindFirst();
	}

	/**
	 * Map -> Stream -> Filter -> String
	 */
	public static void testMapFilter(){
		Map<Integer, String> HOSTING = new HashMap<>();
		HOSTING.put(1, "linode.com");
		HOSTING.put(8, "linode.com");
		HOSTING.put(2, "heroku.com");
		HOSTING.put(3, "digitalocean.com");
		HOSTING.put(4, "aws.amazon.com");

		String string = HOSTING.entrySet().stream() //转成Strem

				.filter(map -> "linode.com".equals(map.getValue()))//过滤值为linnode.com的

				.map(map -> map.getValue())//将map stream 转成 string stream

				.collect(Collectors.joining(",","<",">")); //将结结果集，转换成拼装成‘string’

		System.out.println(string);
	}

	/**
	 * Map -> Stream -> Filter -> Map
	 * 
	 */
	public static void testMapFilter1(){
		Map<Integer, String> HOSTING = new HashMap<>();
		HOSTING.put(1, "linode.com");
		HOSTING.put(8, "linode.com");
		HOSTING.put(2, "heroku.com");
		HOSTING.put(3, "digitalocean.com");
		HOSTING.put(4, "aws.amazon.com");

		Map<Integer, String> newMap = HOSTING.entrySet().stream() //转成Strem
				.filter(map -> "linode.com".equals(map.getValue()))//过滤值为linnode.com的

				.collect(Collectors.toMap(map -> map.getKey(),map -> map.getValue())); //转换成新的map

		System.out.println(newMap);
	}


	/**
	 * Filter findAny  orElse
	 */

	public static void testFindAny(){
		List<Staff> listStaff = Arrays.asList(
				new Staff("mkyong", 30, new BigDecimal(10000)),
				new Staff("jack", 27, new BigDecimal(20000)),
				new Staff("lawrence", 33, new BigDecimal(30000))
				);

		Staff staff = listStaff.stream()   // Convert to steam
				.filter(o -> "jack".equals(o.getName()))  // we want "jack" only
				.findAny()    // If 'findAny' then return found
				.orElse(null);  // If not found, return null


		System.out.println(JSON.toJSONString(staff));
	}
	
	
	/*
	 * 拿匹配条件的第一条
	 */
	public static void testFindFirst(){
		List<Staff> listStaff = Arrays.asList(
				new Staff("mkyong", 30, new BigDecimal(10000)),
				new Staff("jack", 27, new BigDecimal(20000)),
				new Staff("jack", 28, new BigDecimal(20000)),
				new Staff("lawrence", 33, new BigDecimal(30000))
				);

		Staff staff = listStaff.stream()   // Convert to steam
				.filter(o -> "jack".equals(o.getName()))  // we want "jack" only
				.findFirst()    // 匹配条件的第一条
				.orElse(null);  // If not found, return null


		System.out.println(JSON.toJSONString(staff));
	}
}

