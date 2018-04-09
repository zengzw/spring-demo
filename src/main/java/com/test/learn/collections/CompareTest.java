/*
 * @Project Name: springmvcdemo
 * @File Name: CompareTest.java
 * @Package Name: com.test.learn.collections
 * @Date: 2018年3月21日上午10:55:01
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月21日上午10:55:01
 * @see
 */
public class CompareTest {
	
	
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(DateUtils.addDays(date, 1));
		List<Student> listStudent = new ArrayList<>();
		listStudent.add(new Student(0, new Date(), "课时1"));
		listStudent.add(new Student(1, DateUtils.addDays(date, 1), "课时2"));
		listStudent.add(new Student(0, new Date(), "课时3"));
		listStudent.add(new Student(2, new Date(), "课时4"));
		listStudent.add(new Student(1, new Date(), "课时5"));
		
		Collections.sort(listStudent,new StudentCompare());
		
		System.out.println(JSON.toJSONString(listStudent));
		
	}
}
