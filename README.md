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
	
## assertj
- 单元测试

## ThreadPool
### 基础参数
我们先来总结一下线程池的这些参数，后面再上源码就好理解了 
core,maxPoolSize,keepalive 

### 执行任务时流程
1. 如果线程池中线程数量 < core，新建一个线程执行任务； 
2. 如果线程池中线程数量 >= core ,则将任务放入任务队列 
3. 如果线程池中线程数量 >= core 且 < maxPoolSize，且任务队列满了，则创建新的线程； 
4. 如果线程池中线程数量 > core ,当线程空闲时间超过了keepalive时，则会销毁线程；由此可见线程池的队列如果是无界队列，那么设置线程池最大数量是无效的；

### 源码分析java.util.concurrent.ThreadPoolExecutor
这是最常用的一个类，我们建立的线程池大部分都是用它实现的，所以重点来分析下这个类的源码；

### 构造方法
它的构造方法有很多，但是最终调用的都是下面这个构造方法
```
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
```

### 参数说明

- corePoolSize（核心线程池大小）：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程。

- maximumPoolSize（线程池最大大小）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是如果使用了无界的任务队列这个参数就没什么效果。

- ThreadFactory：用于设置创建线程的工厂。 默认使用Executors内部类DefaultThreadFactory，可以通过实现ThreadFactory接口，写自己的Factory，通过线程工厂给每个创建出来的线程设置更有意义的名字，Debug和定位问题时非常又帮助；

- keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。
	 
- TimeUnit（线程活动保持时间的单位）：可选的单位有天（DAYS），小时（HOURS），分钟（MINUTES），毫秒(MILLISECONDS)，微秒(MICROSECONDS, 千分之一毫秒)和毫秒(NANOSECONDS, 千分之一微秒)。
	 
- workQueue（任务队列）：用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列。
 	
		1.ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
		2.LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂法Executors.newFixedThreadPool()使用了这个队列。
		3.SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
		4.PriorityBlockingQueue：一个具有优先级得无限阻塞队列。

- RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。以下是提供的四种策略。
   	
    1.AbortPolicy：直接抛出异常。默认策略
    2.CallerRunsPolicy：只用调用者所在线程来运行任务。
    3.DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
    4.DiscardPolicy：不处理，丢弃掉。
	当然也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。
	
		
