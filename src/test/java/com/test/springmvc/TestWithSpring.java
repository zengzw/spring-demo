package com.test.springmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/xml/application-service.xml" })
@TransactionConfiguration(defaultRollback = true)
public class TestWithSpring{

	protected static final Logger log = LoggerFactory.getLogger(TestWithSpring.class);
	

	@Test
	public void test(){
		log.info("请不要注释掉该测试方法！");
	}
}