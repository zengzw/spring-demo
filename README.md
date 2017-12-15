# spring-demo
## redis
- nothing

## zookeeper
- nothing

## storm
-
 
## Kafka
- nothing

## solr
- nothing

## SPI
- nothing

## JUC
- nothing

## CGLIB
	- nothing
## LTS
	- nothing
## QuartzJob
	- nothing
## Spring redis session

	None
	
## Spring Retry
	- spring 错误重试
	```
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
	```
	
	- spring 熔断
	
## POI Excel
	- 读取Excel转对象
	- 对象写入到Excel
	 
## i18n
	- nothing
	
## RateLimiter
	- nothing
## HttpUnit
	- nothing
## Proxy
	- nothing
## Jsoup
	- nothing
## javassist
	- nothing
	
## NIO
- com.test.learn.nio
	- server	
	
	 	NIOServer
	 
	- client	
	
		 NIOClient
		 NIOClient2
	 
## Netty
- Reference
	- https://waylau.com/essential-netty-in-action/GETTING%20STARTED/The%20What%20and%20Why%20of%20Bootstrapping.html
	- https://waylau.com/netty-4-user-guide/Getting%20Started/Shutting%20Down%20Your%20Application.html
	
## Hystrix
- Refenence
 	http://www.jianshu.com/p/266cba713eb1
- 线程池隔离
- 信号量隔离
	
- 熔断
	-
- 降级回退
	-
		
		
		
