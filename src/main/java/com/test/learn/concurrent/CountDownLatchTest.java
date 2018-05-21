/*
 * @Project Name: springmvcdemo
 * @File Name: CountDownLatchTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月16日下午6:39:40
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Java多线程编程中经常会碰到这样一种场景——某个线程需要等待一个或多个线程操作结束（或达到某种状态）才开始执行。
 * 比如开发一个并发测试工具时，主线程需要等到所有测试线程均执行完成再开始统计总共耗费的时间，此时可以通过CountDownLatch轻松实现。
 * 
 * 
 * @author zengzw-1220
 * @date 2018年5月16日下午6:39:40
 * @see
 */
public class CountDownLatchTest {
	
	 public static void main(String[] args) throws InterruptedException {
		    int totalThread = 3;
		    long start = System.currentTimeMillis();
		    CountDownLatch countDown = new CountDownLatch(totalThread);
		    
		    for(int i = 0; i < totalThread; i++) {
		      final String threadName = "Thread " + i;
		      
		      new Thread(() -> {
		        System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
		        
		        try {
		          Thread.sleep(1000);
		        } catch (Exception ex) {
		          ex.printStackTrace();
		        }
		        
		        countDown.countDown();
		        
		        System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
		        
		      }).start();;
		    }
		    
		    countDown.await();
		    
		    long stop = System.currentTimeMillis();
		    System.out.println(String.format("Total time : %sms", (stop - start)));
		  }
}
