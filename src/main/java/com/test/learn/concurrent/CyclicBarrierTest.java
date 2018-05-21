/*
 * @Project Name: springmvcdemo
 * @File Name: CyclicBarrierTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月16日下午6:34:11
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * 在《当我们说线程安全时，到底在说什么》一文中讲过内存屏障，它能保证屏障之前的代码一定在屏障之后的代码之前被执行。CyclicBarrier可以译为循环屏障，也有类似的功能。
 * CyclicBarrier可以在构造时指定需要在屏障前执行await的个数，所有对await的调用都会等待，直到调用await的次数达到预定指，所有等待都会立即被唤醒。
         从使用场景上来说，CyclicBarrier是让多个线程互相等待某一事件的发生，然后同时被唤醒。而上文讲的CountDownLatch是让某一线程等待多个线程的状态，然后该线程被唤醒。
 *
 * 
 * 像50米比赛，参赛人都等着枪声响，才可以往前跑。
 * 
 * @author zengzw-1220
 * @date 2018年5月16日下午6:34:11
 * @see
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
	    int totalThread = 5;
	    CyclicBarrier barrier = new CyclicBarrier(totalThread);
	    
	    for(int i = 0; i < 4; i++) {
	      String threadName = "Thread " + i;
	      new Thread(() -> {
	        System.out.println(String.format("%s\t%s %s", new Date(), threadName, " is waiting"));
	        try {
	          barrier.await();
	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }
	        System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
	      }).start();
	    }
	    
	    new Thread(() ->{
	    	try {
				Thread.sleep(5000);
				System.out.println(Thread.currentThread()+"我也到了。。。。");
		    	barrier.await();
		    	
		    	System.out.println(Thread.currentThread() +" 我跑完啦。");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	System.out.println(Thread.currentThread()+"我也到了。。。。");
	    }).start();
	    
	    
	  }
}
