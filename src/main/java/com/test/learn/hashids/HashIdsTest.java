/*
 * @Project Name: springmvcdemo
 * @File Name: HashIdsTest.java
 * @Package Name: com.test.learn.hashids
 * @Date: 2018年3月8日上午11:48:19
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.hashids;

import java.util.Arrays;

import org.hashids.Hashids;

import io.netty.handler.codec.http.HttpResponseDecoder;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月8日上午11:48:19
 * @see
 */
public class HashIdsTest {
	
	public static void main(String[] args) {

		//第一个参数，是盐值，用来混淆；第二个参数，是生成的长度,第三个参数，加密数生成范围
		Hashids hashids = new Hashids("this is my salt.",10,"0123456789abcdefghijklmnopqrstuvwxyz");
		String text = hashids.encode(1L);
		
		System.out.println(text);
		
		long[] numbers = hashids.decode(text);
		System.out.println(Arrays.toString(numbers));
		
//		HttpObjectAggregator
//		HttpResponseDecoder
		
	}
}
