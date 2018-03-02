/*
 * @Project Name: springmvcdemo
 * @File Name: DateTimeTest.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年2月2日下午5:06:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月2日下午5:06:49
 * @see
 */
public class DateTimeTest {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		System.out.println(LocalDate.now());
	    LocalDate date1 = LocalDate.of(2009, 12, 31);
	    LocalDateTime date2 = LocalDateTime.now();
	    
	    System.out.println(date2);
	    System.err.println(date2.format(dtf));
	    System.out.println(dtf.format(date2));
	}
}
