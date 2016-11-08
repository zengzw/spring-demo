package com.test.springmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
@TransactionConfiguration(defaultRollback = true)
public class TestWithSpring{

	protected Logger log = LoggerFactory.getLogger("thread-test");
	

	@Test
	public void test(){
		log.info("请不要注释掉该测试方法！");
	}
}