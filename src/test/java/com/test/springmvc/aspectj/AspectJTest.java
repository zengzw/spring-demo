/*
 * @Project Name: springmvcdemo
 * @File Name: AspectJTest.java
 * @Package Name: com.test.springmvc.aspectj
 * @Date: 2018年1月5日下午3:58:21
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.aspectj;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月5日下午3:58:21
 * @see
 */
public class AspectJTest {

	@Test
	public void test(){
		//		assertThat("abc").isEqualTo("abc");
		assertThat(123).getWritableAssertionInfo();
	}


	@Test
	public void testString(){
		String result = null;
		String name = "tea";

		//判断是否为null 或者 空
		assertThat(result).isNullOrEmpty();

		assertThat("").isEmpty();

		//名字是否相等，忽略大小写，忽略空格
		assertThat(name).isEqualTo("tea").isEqualToIgnoringCase("Tea")
		.isEqualToIgnoringWhitespace("tea ");

		//开始字符，结束字符，长度
		assertThat(name).startsWith("t").endsWith("a").hasSize(3);

		//包含 与 不包含
		assertThat(name).contains("e").doesNotContain("b");

		//只包含一次
		assertThat(name).containsOnlyOnce("e");

		//正则表达公匹配
		assertThat(name).matches(".e.").doesNotMatch(".*d");
	}


	/**
	 * 
	 * 测试数字
	 * 
	 * @date 2018年1月9日上午11:55:02
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	@Test
	public void testDigit(){
		int num = 20;
		Integer result = null;

		assertThat(result).isNull();

		//等于
		assertThat(num).isEqualTo(20);

		//区间
		assertThat(num).isBetween(10,30);

		//大于，小于，大于等于
		assertThat(num).isGreaterThan(10) //大于 
		.isLessThan(30) //小于
		.isGreaterThanOrEqualTo(19) //大于等于
		.isLessThanOrEqualTo(21); //小于等于


		//断言是否等于0
		assertThat(0).isZero();

		assertThat(num).isNotZero();

		//断言正数，非负数
		assertThat(num).isPositive().isNotNegative();

		assertThat(-1).isNegative().isNotPositive();

	}


	@Test
	public void testList(){
		List listResult = new ArrayList<>();

		assertThat(listResult).isEmpty();

		listResult.addAll(Arrays.asList(1,2,3));

		// 断言 列表的开始 结束元素
		assertThat(listResult).startsWith(1).endsWith(3);

		// 断言 列表包含元素 并且是排序的
		assertThat(listResult).contains(1,atIndex(0))
		.contains(2,atIndex(1))
		.isSorted();


		// 断言 被包含与给定列表
		assertThat(listResult).isSubsetOf(Arrays.asList(1,2,3,4));

		// 断言 存在唯一元素
		assertThat(listResult).containsOnlyOnce(1);

	}



	@Test
	public void testMap(){
		Map<String, Object> foo = Maps.newHashMap();
		foo.put("A", 1);
		foo.put("B", 2);
		foo.put("C", 3);
		
		assertThat(foo).isNotEmpty().hasSize(3);
		
		   // 断言 map 包含元素
		assertThat(foo).contains(entry("A", 1));
		
		//包含Key
		assertThat(foo).containsKey("A");
		
		//包含Value
		assertThat(foo).containsValue(1);
	}
	
	
	@Test
	public void testError(){
		assertThat("a").as("测试error").isEqualTo("b").contains("c");
	}
}
