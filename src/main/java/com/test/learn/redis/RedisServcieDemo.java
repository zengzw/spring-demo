/*
 * @Project Name: springmvcdemo
 * @File Name: RedisDemo.java
 * @Package Name: com.test.learn.redis
 * @Date: 2018年6月4日下午6:30:47
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月4日下午6:30:47
 * @see
 */
@Service
public class RedisServcieDemo {

	@Autowired
	private RedisTemplate redisTemplate;
	

	public void test(){
		RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
		System.out.println(redisConnection);
	}


	
	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
	
		this.redisTemplate = redisTemplate;
	}
}
