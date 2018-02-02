/*
 * @Project Name: springmvcdemo
 * @File Name: JoinArrayTest.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年2月1日下午4:41:29
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 素组合并
 * 
 * @author zengzw-1220
 * @date 2018年2月1日下午4:41:29
 * @see
 */
public class JoinArrayTest {
	
	public static void main(String[] args) {
        String[] s1 = new String[]{"a", "b", "c"};
        String[] s2 = new String[]{"d", "e", "f"};
        String[] s3 = new String[]{"g", "h", "i"};
        
        //转成流素组 -> 合并流 -> 素组
        String[] aray = Stream.of(s1,s2,s3).flatMap(Stream::of).toArray(String[]::new);
        System.out.println(Arrays.toString(aray));
        
        
        int [] int1 = new int[]{1,2,3};
        int[] int2 = new int[]{4,5,6};
        int[] int3 = new int[]{7,8,9};
        int[] result2 = IntStream.concat(Arrays.stream(int1), Arrays.stream(int2)).toArray();
        
        System.out.println(Arrays.toString(result2));
        
        int[] result3 = IntStream.concat(IntStream.concat(Arrays.stream(int1), Arrays.stream(int2)), Arrays.stream(int3)).toArray();
        System.out.println(Arrays.toString(result3));
        }
}
