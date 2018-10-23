package com.test.springmvc.model;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class TestBean implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("-----------testBean--------------AfterSet");
		
	}

}
