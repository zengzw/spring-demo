/*
 * @Project Name: springmvcdemo
 * @File Name: IRedisLock.java
 * @Package Name: com.test.learn.redis
 * @Date: 2018年4月20日下午4:14:21
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.redis;

/**
 *
 * @author zengzw-1220
 * @date 2018年4月20日下午4:14:21
 * @see
 */
public interface IRedisLock {
	
	 /**
		 * 获取锁
		 * @param lock
		 *  @return {@code true} 若成功获取到锁, {@code false} 
		 */
		boolean lock(String lock);
		  
		 /**
		 * 释放锁，如果锁已超时
		 * @param lock
		 */
		 void tryUnlock(String lock);
		 /**
		  * 释放锁
		  * 
		  * @param lock
		  */
		 void unLock(String lock);
		 
		 
		 /**
		 * 超时自动返回的阻塞性的获取锁, 不响应中断
		 * 
		 * @param time 单位毫秒
		 * @return {@code true} 若成功获取到锁, {@code false} 若在指定时间内未取到锁
		  * 
		 */
		 boolean tryLock(String lock, long time);
}
