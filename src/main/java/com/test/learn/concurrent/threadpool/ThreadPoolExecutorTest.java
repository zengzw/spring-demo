/*
 * @Project Name: springmvcdemo
 * @File Name: ThreadPoolExecutorTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年2月27日下午5:18:26
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月27日下午5:18:26
 * @see
 */
public class ThreadPoolExecutorTest {
	public static void main(String[] args) {  
		
		/*
		 执行任务时 
			1. 如果线程池中线程数量 < core，新建一个线程执行任务； 
			2. 如果线程池中线程数量 >= core ,则将任务放入任务队列 
			3. 如果线程池中线程数量 >= core 且 < maxPoolSize，且任务队列满了，则创建新的线程； 
			4. 如果线程池中线程数量 > core ,当线程空闲时间超过了keepalive时，则会销毁线程；由此可见线程池的队列如果是无界队列，那么设置线程池最大数量是无效的；
		 */
		
		//15个任务的情况下：5个线程执行，再进来的，放到队列里头；队列5个满了之后，如果还多的，会再创建5个临时线程来执行其中的5个任务。
			ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 20, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(5));

		//LinkedBlockingQueue  线程数只有5个，多余的任务添加到队列当中。当线程执行完了，再从队列中拿数据执行。
	/*	ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 20, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
*/

		//SynchronousQueue,在某次添加元素后必须等待其他线程取走后才能继续添加.
		//当任务完成后，线程的大小 大于 线程池(corePoolSize)的大小时，将会把大于corePoolSize的线程回收，超过keepAliveTime的线程
		/*ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 20, 20, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());*/


		for(int i=0;i< 15;i++){
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);

			System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
					executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());

		}

		//executor.shutdown();

		}
}

class MyTask implements Runnable {
	private int taskNum;

	public MyTask(int num) {
		this.taskNum = num;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" : 正在执行task "+taskNum);
		try {
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":task "+taskNum+"执行完毕");
	}
}
