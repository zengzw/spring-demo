/*
 * @Project Name: springmvcdemo
 * @File Name: SuiteClass.java
 * @Package Name: com.test.springmvc.multictest
 * @Date: 2018年1月9日上午10:22:01
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.multictest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 测试组件，一次执行多个单元测试类
 * 
 * 
 * @author zengzw-1220
 * @date 2018年1月9日上午10:22:01
 * @see
 */
@RunWith(Suite.class)
@SuiteClasses({ATest.class,BTest.class})
public class SuiteClass {
	
	
}
