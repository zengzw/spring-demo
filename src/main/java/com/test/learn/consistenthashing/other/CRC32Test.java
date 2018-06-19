/*
 * @Project Name: springmvcdemo
 * @File Name: CRC32Test.java
 * @Package Name: com.test.learn.consistenthashing.other
 * @Date: 2018年6月13日下午2:30:55
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.consistenthashing.other;

import java.util.zip.CRC32;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月13日下午2:30:55
 * @see
 */
public class CRC32Test {

	public static void main(String[] args) {

		System.out.println(getHash("server1"));	
		
		System.out.println(Math.pow(2, 32));
	}


	public static long getHash(String serverNodeName) {
		CRC32 crc32 = new CRC32();
		crc32.update(serverNodeName.getBytes());
		return crc32.getValue();
	}
}
