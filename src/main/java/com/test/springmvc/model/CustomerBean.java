/*
 * @Project Name: springmvcdemo
 * @File Name: CustomerBean.java
 * @Package Name: com.test.springmvc.service
 * @Date: 2018年5月29日下午2:56:26
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月29日下午2:56:26
 * @see
 */
public class CustomerBean implements BeanPostProcessor,BeanNameAware,InitializingBean,DisposableBean,BeanFactoryAware
,ApplicationContextAware,ApplicationListener{
	

	public CustomerBean() {
		
		System.out.println("customer bean constrcut......");
		
	}
	
	public void setName(String name){
		System.out.println("setName value:"+name);
	}
	
	
	public void init(){
		System.out.println("init-----method");
	}
	
	
	public void destory(){
		System.out.println("destory----method");
	}
	
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("BeanPostProcessor#before-----"+beanName +" :"+bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor#after-----"+beanName +" :"+bean);
		
		return bean;
	}

	@Override
	public void setBeanName(String name) {

	System.out.println("BeanNameAware#setBeanName----:"+name);
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	System.out.println("InitializingBean#afterPropertiesSet---");
		
	}

	@Override
	public void destroy() throws Exception {

		System.out.println("DisposableBean#destroy------");
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

	System.out.println("BeanFactoryAware#setBeanFactory---"+beanFactory);
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		System.out.println("ApplicationContextAware#setApplicationContext:"+applicationContext);
		
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("ApplicationListener#onApplicationEvent:"+event);
		
	}
}
