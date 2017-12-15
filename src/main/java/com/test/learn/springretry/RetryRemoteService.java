/*
 * @Project Name: springmvcdemo
 * @File Name: RetryRemoteService.java
 * @Package Name: com.test.learn.springretry
 * @Date: 2017年12月13日下午6:40:28
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.springretry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.test.base.exception.BusinessRuntimeException;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年12月13日下午6:40:28
 * @see
 */
@Service
@EnableRetry
public class RetryRemoteService {

	/**
	 * 
	 * 
	 * @Retryable注解 
		被注解的方法发生异常时会重试 
		value:指定发生的异常进行重试 
		include:和value一样，默认空，当exclude也为空时，所有异常都重试 
		exclude:指定异常不重试，默认空，当include也为空时，所有异常都重试 
		maxAttemps:重试次数，默认3 
		backoff:重试补偿机制，默认没有

		@Backoff注解 
		delay:指定延迟后重试 
		multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒

		@Recover 
		当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调
	 * 
	 * 
	 * @date 2017年12月13日下午6:44:26
	 * @author zengzw-1220
	 * @throws Exception 
	 * @since 1.0.0
	 */
	@Retryable(value =  {RemoteAccessException.class},maxAttempts = 3,backoff = @Backoff(delay = 2000l,multiplier = 1))
	public void call() throws Exception{
		System.out.println("---------------");
		throw new RemoteAccessException("RPC调用异常");
	}

	@Recover
	public void recover(RemoteAccessException e){
		System.out.println("---retery on.....");
		System.out.println(e.getMessage());
	}


	/**
	 * 
	 * CircuitBreaker：用于方法，实现熔断模式。 
		include 指定处理的异常类。默认为空 
		exclude指定不需要处理的异常。默认为空 
		vaue指定要重试的异常。默认为空 
		maxAttempts 最大重试次数。默认3次 
		openTimeout 配置熔断器打开的超时时间，默认5s，当超过openTimeout之后熔断器电路变成半打开状态（只要有一次重试成功，则闭合电路） 
		resetTimeout 配置熔断器重新闭合的超时时间，默认20s，超过这个时间断路器关闭
		
		
	 * @date 2017年12月14日下午2:28:15
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	@CircuitBreaker(maxAttempts=3,openTimeout=1000,resetTimeout=2000,label="test-CircuitBreaker",include=BusinessRuntimeException.class,exclude=Exception.class)
	public void circuitBreaker(int num){
		System.err.println(" 进入重试方法num="+num);
		if(num>10)return;
		 
		throw new BusinessRuntimeException("", "出错了。");
	}
}
