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

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

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
}
