/*
 * @Project Name: springmvcdemo
 * @File Name: ConcurrentLinkedDeque.java
 * @Package Name: com.test.learn.concurrent.queue
 * @Date: 2018年3月2日上午10:43:41
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月2日上午10:43:41
 * @see
 */
public class ConcurrentLinkedDequeTest {
	
	public static void main(String[] args) {

		ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque();
		
		
		
		//删除元素
		System.out.println(queue.poll());
		System.out.println(queue.size());
		
		
		System.out.println(queue.peek());
		System.out.println(queue.size());
	
		//数据为空，空指针
		/*	System.out.println(queue.getFirst());
		System.out.println(queue.size());*/
		
		
		//删除元素。数据为空，报错
		/*System.out.println(queue.pop());
		System.out.println(queue.size());
		*/
		
		//数据为空，报错
	/*	System.out.println(queue.element());
		System.out.println(queue.size());
		*/
	}
}
