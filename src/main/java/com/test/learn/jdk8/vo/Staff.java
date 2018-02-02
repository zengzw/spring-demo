/*
 * @Project Name: springmvcdemo
 * @File Name: Staff.java
 * @Package Name: com.test.learn.jdk8.vo
 * @Date: 2018年1月30日下午2:13:45
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.vo;

import java.math.BigDecimal;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月30日下午2:13:45
 * @see
 */
public class Staff {

	public Staff(String name,int age,BigDecimal salary){
		this.name = name;
		this.age = age;
		this.salary =salary;
	}

	private String name;
	private int age;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public int getAge() {

		return age;
	}

	public void setAge(int age) {

		this.age = age;
	}

	public BigDecimal getSalary() {

		return salary;
	}

	public void setSalary(BigDecimal salary) {

		this.salary = salary;
	}
	private BigDecimal salary;
}
