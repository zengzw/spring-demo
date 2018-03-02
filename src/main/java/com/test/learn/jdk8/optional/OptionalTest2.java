/*
 * @Project Name: springmvcdemo
 * @File Name: OptionalTest2.java
 * @Package Name: com.test.learn.jdk8.optional
 * @Date: 2018年2月2日下午5:47:41
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.optional;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Optional;

import clojure.xml__init;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月2日下午5:47:41
 * @see
 */
public class OptionalTest2 {

	public static void main(String[] args) {

		Optional<String> gender = Optional.of("Male");
		System.out.println("optional get:"+gender.get());
		System.out.println("optional empty:"+gender.empty().orElse("hello empty"));


		String quesion = "How are you?";
		String answer = null;

		//OfNullable 如果不为空，返回Optional.值 .否则，返回Optional.empty
		System.out.println("optional ofNullable:"+Optional.ofNullable(quesion)); //
		System.out.println("optional ofNullable:"+Optional.ofNullable(answer));

		//		System.out.println(Optional.of(answer)); //抛异常，不允许为空

		testOptional1();
		
		
		testOption2();
		
		testOption3();
	}


	public static void testOptional1(){
		System.out.println("-----------optional1 map  flatMap----------");
		Optional<String> nonEmptyGender = Optional.of("male");
		Optional<String> emptyGender = Optional.empty();


		//转成大写
		Optional<String> optional = nonEmptyGender.map(String::toUpperCase);
		System.out.println("not empty case:"+optional.get());
		System.out.println("empty case :"+emptyGender.map(String::toUpperCase));

		Optional<Optional<String>> nonEmptyOtionalGender = Optional.of(Optional.of("male"));
		System.out.println(nonEmptyOtionalGender);
		System.out.println(nonEmptyOtionalGender.map(o -> o.map(String::toUpperCase)));
		System.out.println(nonEmptyOtionalGender.flatMap(o -> o.map(String::toUpperCase)));

	}


	public static void testOption2(){
		System.out.println("----------option 2 ifPresent  isPresent---------------");
		Optional<String> gender = Optional.of("MALE");
		Optional<String> emptyGender = Optional.empty();
		
		if(gender.isPresent()){
			System.out.println("isPresent:"+gender.get());
		}else{
			System.out.println("value not avaliable");
		}
		
		
		gender.ifPresent(System.out::println);
		
		emptyGender.ifPresent(x -> System.out.println("is empty"));

	}
	
	
	public static void testOption3(){
		System.out.println("----------option 2 orElse  orElseGet---------------");
		Optional<String> gender = Optional.of("MALE");
		Optional<String> emptyGender = Optional.empty();
		
		System.out.println(gender.orElse("<none>"));
		System.out.println(emptyGender.orElse("<none>"));
		
		System.out.println(gender.orElseGet(() -> "hello not empty"));
		System.out.println(emptyGender.orElseGet(()->"hello empty"));
	}


}
