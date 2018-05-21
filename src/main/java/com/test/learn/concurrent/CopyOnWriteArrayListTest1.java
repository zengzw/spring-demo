/*
 * @Project Name: springmvcdemo
 * @File Name: CopyOnWriteArrayListTest1.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月17日下午12:22:23
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *它也属于Java集合框架的一部分，是ArrayList的线程安全的变体，跟ArrayList的不同在于：CopyOnWriteArrayList针对数组的修改操作（add、set等）是基于内部拷贝的一份数据而进行的。
 *换句话说，即使在一个线程进行遍历操作时有其他线程可能进行插入或删除操作，我们也可以“线程安全”得遍历CopyOnWriteArrayList。
 *
 * @author zengzw-1220
 * @date 2018年5月17日下午12:22:23
 * @see
 */
public class CopyOnWriteArrayListTest1 {

	/**
	 * 读线程
	 * @author wangjie
	 *
	 */
	private static class ReadTask implements Runnable {
		List<String> list;

		public ReadTask(List<String> list) {
			this.list = list;
		}

		public void run() {
			System.out.println("read:"+list);
			for (String str : list) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("read:"+str);
			}
		}
	}
	/**
	 * 写线程
	 * @author wangjie
	 *
	 */
	private static class WriteTask implements Runnable {
		List<String> list;
		int index;

		public WriteTask(List<String> list, int index) {
			this.list = list;
			this.index = index;
		}

		public void run() {
			list.remove(index);
			list.add(index, "write_" + index);
			System.out.println("write:"+list);
		}
	}

	public void run() {
		final int NUM = 10;
		List<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i < NUM; i++) {
			list.add("main_" + i);
		}
		ExecutorService executorService = Executors.newFixedThreadPool(NUM);
		for (int i = 0; i < NUM; i++) {
			executorService.execute(new ReadTask(list));
			executorService.execute(new WriteTask(list, i));
		}
		executorService.shutdown();
	}

	public static void main(String[] args) {
		new CopyOnWriteArrayListTest1().run();
//		testExceptionThrow();
	}




	/**
	 * 
	 * 不支持 iterator 刪除
	 * @date 2018年5月17日下午12:25:16
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	private static void testExceptionThrow() {
		CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 78});
		Iterator<Integer> integerIterator = numbers.iterator();
		while (integerIterator.hasNext()) {
			integerIterator.remove();
		}
	}

}
