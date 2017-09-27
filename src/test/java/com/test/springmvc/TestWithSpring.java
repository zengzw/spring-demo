package com.test.springmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.test.learn.rpc.RpcProxy;
import com.test.learn.rpc.demo.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/xml/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = true)
public class TestWithSpring{

	protected Logger log = LoggerFactory.getLogger("thread-test");
	

	@Test
	public void test(){
		log.info("请不要注释掉该测试方法！");
	}
}