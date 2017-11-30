/*
 * @Project Name: springmvcdemo
 * @File Name: GetStockServiceCommoand.java
 * @Package Name: com.test.learn.hystrix
 * @Date: 2017年11月29日下午2:21:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.test.base.exception.BusinessRuntimeException;
import com.test.learn.hystrix.service.StockService;

/**
 * 
 * 一般实践中，我们将一个接口中的所有方法都用不同的命令key区分，组key采用类名，线程池则根据需要选择性的采用共享线程池或独立线程池。
 * 
 * @author zengzw-1220
 * @date 2017年11月29日下午2:21:49
 * @see
 */
public class GetStockServiceCommoand extends HystrixCommand<String>{

	
	//测试
	private StockService service = new StockService();
	
	protected GetStockServiceCommoand() {
		super(getSetter());
	}

	
	private static Setter getSetter(){
		//服务分组。相同分组的服务会聚集在一起
		HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("stock");
		
		//服务标识
		HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("getStock");
		
		//线程池名称。相同线程池名称的线程池是同一个。
		HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("sotck-pool");
		
		//线程池配置。
		HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter()
				.withCoreSize(10)// 核心线程池大小和线程池最大大小
				.withKeepAliveTimeMinutes(5) //线程池空闲线程生存时间。
				.withMaxQueueSize(Integer.MAX_VALUE) //配置线程池最大大小
				.withQueueSizeRejectionThreshold(2000); //当等待队列多大的时候，将会执行决绝策略
		
		//命令属性配置。使用线程池隔离
		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
				.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
		
		//信号量隔离。
		/*HystrixCommandProperties.Setter commandSemaphoreProperties = HystrixCommandProperties.Setter()
				.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
				.withExecutionIsolationSemaphoreMaxConcurrentRequests(50); //限制并发总数
		*/
		return HystrixCommand.Setter.withGroupKey(groupKey)
				.andCommandKey(commandKey)
				.andThreadPoolKey(threadPoolKey)
				.andThreadPoolPropertiesDefaults(threadPoolProperties)
				.andCommandPropertiesDefaults(commandProperties);
	}
	@Override
	protected String run() throws Exception {
		/*
		 * 模拟服务异常，不可用。此时，会调用getFallback的返回
		 */
	/*	if(true)
			throw new BusinessRuntimeException("", "");*/
		Thread.sleep(1000);
		return service.getStock();
	}
	
	
	/**
	 * 
	 * 降级方式，快速失败 
	 * @see com.netflix.hystrix.HystrixCommand#getFallback()
	 */
	@Override
	protected String getFallback() {
	
		return "{success:0,content:[]}";
	}
}	
