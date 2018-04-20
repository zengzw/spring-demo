/*
 * @Project Name: springmvcdemo
 * @File Name: StreamGroupbyTest.java
 * @Package Name: com.test.learn.jdk8.collections
 * @Date: 2018年1月30日下午3:10:59
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.collections;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.test.learn.jdk8.vo.Staff;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月30日下午3:10:59
 * @see
 */
public class StreamGroupbyTest {

	public static void main(String[] args) {
		List<String> items =
				Arrays.asList("apple", "apple", "banana",
						"apple", "orange", "banana", "papaya");

		Map<String, Long> map = items.stream()
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(map);


		/**
		 * 排序
		 */
		Map<String, Long> finalMap = new LinkedHashMap<>();
		map.entrySet().stream()
		.sorted(Map.Entry.<String,Long>comparingByValue().reversed())
		.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
		System.out.println(finalMap);


		/**
		 * 根据名词分组，统计年龄
		 */
		List<Staff> staff = Arrays.asList(
				new Staff("mkyong", 30, new BigDecimal(10000)),
				new Staff("mkyong", 40, new BigDecimal(100000)),
				new Staff("jack", 27, new BigDecimal(20000)),
				new Staff("lawrence", 33, new BigDecimal(30000))
				);

		Map<String, Integer> result = staff.stream().collect(Collectors.groupingBy(Staff::getName,Collectors.summingInt(Staff::getAge)));
		System.out.println(result);


		//group by price
		List<Staff> staffItems = Arrays.asList(
				new Staff("apple", 10, new BigDecimal("9.99")),
				new Staff("banana", 20, new BigDecimal("19.99")),
				new Staff("orang", 10, new BigDecimal("29.99")),
				new Staff("watermelon", 10, new BigDecimal("29.99")),
				new Staff("papaya", 20, new BigDecimal("9.99")),
				new Staff("apple", 10, new BigDecimal("9.99")),
				new Staff("banana", 10, new BigDecimal("19.99")),
				new Staff("apple", 20, new BigDecimal("9.99"))
				);
		
        Map<BigDecimal, List<Staff>> groupByPriceMap =
        		staffItems.stream().collect(Collectors.groupingBy(Staff::getSalary));

        System.out.println(JSON.toJSONString(groupByPriceMap));

        
        //转换成set
        Map<Integer, Set<String>> resultMap = staffItems.stream().collect(
        		Collectors.groupingBy(Staff::getAge,
        				Collectors.mapping(Staff::getName, Collectors.toSet())));
        
        
        System.out.println(JSON.toJSONString(resultMap));
	}
}
