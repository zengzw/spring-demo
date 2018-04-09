/*
 * @Project Name: springmvcdemo
 * @File Name: InterThreadCommunication.java
 * @Package Name: com.test.learn.mthread
 * @Date: 2018年3月27日上午11:59:27
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.mthread;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月27日上午11:59:27
 * @see
 */
public class InterThreadCommunication {

	boolean wasSignalled = false;


	public void doWait(InterThreadCommunication interThreadCommunication) throws InterruptedException{
		synchronized (interThreadCommunication) {
			while(!wasSignalled){
				System.out.println("----wait before----"+Thread.currentThread().getName());
				interThreadCommunication.wait();
			}
			System.out.println("---wait after---"+Thread.currentThread().getName());
			wasSignalled = false;
		}
	}


	public void doNotify(InterThreadCommunication interThreadCommunication){
		synchronized (interThreadCommunication) {
			wasSignalled = true;
			interThreadCommunication.notify();
			System.out.println("---notify after---");
		}
	}



	public static void main(String[] args) {
		InterThreadCommunication interThreadCommunication = new InterThreadCommunication();
		new Thread(()-> {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			interThreadCommunication.doNotify(interThreadCommunication);
		}).start();



		new Thread(()-> {
			try {
				interThreadCommunication.doWait(interThreadCommunication);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
/*
		new Thread(()-> {
			try {
				interThreadCommunication.doWait(interThreadCommunication);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();*/
	}
}
