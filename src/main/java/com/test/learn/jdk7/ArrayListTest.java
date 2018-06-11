/*
 * @Project Name: springmvcdemo
 * @File Name: ArrayListTest.java
 * @Package Name: com.test.learn.jdk7
 * @Date: 2018年6月8日下午3:50:56
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月8日下午3:50:56
 * @see
 */
public class ArrayListTest {
	
	public static void main(String[] args) {

//		List<String> listReuslt = Arrays.asList("a","b","c","d");
		List<String> listReuslt = new ArrayList<>();
		listReuslt.add("a");
		listReuslt.add("b");
		listReuslt.add("c");
		
		
		for(int i = 0; i<listReuslt.size() ;i++){
			System.out.println(listReuslt.get(i));
			
			listReuslt.add(i+"");
			if(i > 15){
				break;
			}
		}
		
		for(String a:listReuslt){
			System.out.println(a);
			listReuslt.add("cadd");
		}
	}
}
