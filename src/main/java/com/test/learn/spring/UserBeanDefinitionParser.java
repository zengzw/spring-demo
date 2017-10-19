/*
 * @Project Name: springmvcdemo
 * @File Name: UserBeanDefinitionParser.java
 * @Package Name: com.test.learn.spring
 * @Date: 2017年10月10日下午4:55:16
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年10月10日下午4:55:16
 * @see
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser{
	
	@Override
	protected Class<?> getBeanClass(Element element) {
	
		// TODO Auto-generated method stub
		return super.getBeanClass(element);
	}
	
	
	
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
	
		super.doParse(element, builder);
	}
	
	
}
