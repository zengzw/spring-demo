/*
 * @Project Name: springmvcdemo
 * @File Name: StreamsMapTest.java
 * @Package Name: com.test.learn.jdk8.collections
 * @Date: 2018年1月30日下午2:22:53
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.collections;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.test.learn.jdk8.vo.Staff;
import com.test.learn.jdk8.vo.StaffPublic;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月30日下午2:22:53
 * @see
 */
public class StreamsMapTest {
	public static void main(String[] args) {

		objectToString();
		
		objectToObject();
	}


	/*
	 * 对象 转 String
	 */
	public static void objectToString(){
		List<Staff> staff = Arrays.asList(
				new Staff("mkyong", 30, new BigDecimal(10000)),
				new Staff("jack", 27, new BigDecimal(20000)),
				new Staff("lawrence", 33, new BigDecimal(30000))
				);

		List<String> listStr = staff.stream().map(t -> t.getName()).collect(Collectors.toList());
		System.out.println(JSON.toJSONString(listStr));
	}


	/*
	 * 对象转 对象
	 */

	public static void objectToObject(){
		List<Staff> staff = Arrays.asList(
				new Staff("mkyong", 30, new BigDecimal(10000)),
				new Staff("jack", 27, new BigDecimal(20000)),
				new Staff("lawrence", 33, new BigDecimal(30000))
				);

		List<StaffPublic> liStaffPublics = staff.stream().map( t ->{
			StaffPublic staffPublic = new StaffPublic();
			staffPublic.setAge(t.getAge());
			staffPublic.setName(t.getName());
			return staffPublic;
		}).collect(Collectors.toList());
		
		
		System.out.println(JSON.toJSONString(liStaffPublics));
	}


}
