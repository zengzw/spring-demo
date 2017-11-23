/*
 * @Project Name: springmvcdemo
 * @File Name: DaemonTest.java
 * @Package Name: com.test.learn.mthread
 * @Date: 2017年11月22日下午3:55:57
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.mthread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * 用个比较通俗的比如，任何一个守护线程都是整个JVM中所有非守护线程的保姆：
 * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
   Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用就是 GC (垃圾回收器)，它就是一个很称职的守护者。
 * 
(1) thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程。
(2) 在Daemon线程中产生的新线程也是Daemon的。 
(3) 不要认为所有的应用都可以分配给Daemon来进行服务，比如读写操作或者计算逻辑。 

 * @author zengzw-1220
 * @date 2017年11月22日下午3:55:57
 * @see
 */
public class DaemonTest {
	
	public static void main(String[] args) {

		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					File file = new File("E://deamon.txt");
					 FileOutputStream os=new FileOutputStream(file,true);     
	                  os.write("daemon".getBytes());  
				} catch (InterruptedException | IOException e) {
				
					e.printStackTrace();
				}
			}
		});
		thread.setDaemon(true);                                 
		thread.start();
		System.out.println("end-----");
	}
}
