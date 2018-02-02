/*
 * @Project Name: springmvcdemo
 * @File Name: FileTest.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年2月1日下午4:02:31
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月1日下午4:02:31
 * @see
 */
public class FileTest {

	public static void main(String[] args) {
		String fileName = "e://java8.txt";

		try(Stream<String> stream = Files.lines(Paths.get(fileName))){
			stream.forEach(s -> System.out.println(s));
		}catch (Exception e) {
			e.printStackTrace();
		}


		List<String> list = new ArrayList<>();
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))){

			list =reader.lines().collect(Collectors.toList());

		}catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(list);
	}
}
