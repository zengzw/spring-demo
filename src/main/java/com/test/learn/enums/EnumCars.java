/*
 * @Project Name: springmvcdemo
 * @File Name: EnumCars.java
 * @Package Name: com.test.learn.enums
 * @Date: 2018年3月1日上午9:52:15
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.enums;

import java.util.EnumMap;
import java.util.EnumSet;

import clojure.edn__init;

/**
 * 枚举 实现接口
 * 
 * 
 * @author zengzw-1220
 * @date 2018年3月1日上午9:52:15
 * @see
 */
public enum EnumCars implements DefineEnumInteface{

	BMW("speed"){
		@Override
		public String setDefine(String a) {
			String value  = a + "-BMW";
			return value;
		}
	},

	Benz("stable"){

		@Override
		public String setDefine(String a) {

			return a + "-Benz";
		}
	};

	private String name;


	public String getName() {

		return name;
	}


	public void setName(String name) {

		this.name = name;
	}


	private EnumCars(String name) {
		this.name = name;
	}


	public static void main(String[] args) {

		String value = EnumCars.Benz.setDefine("you are so good.");
		System.out.println(value);


		/*
		 * EnumSet
		 */
		EnumSet<EnumCars> setEnumCars = EnumSet.allOf(EnumCars.class);
		for(EnumCars e : setEnumCars){
			System.err.println(e);
		}

		EnumMap<EnumCars, String> map = null;
	}

}
