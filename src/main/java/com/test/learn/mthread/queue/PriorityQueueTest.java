/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.queue;

import java.util.PriorityQueue;

/**
 *
 * @author zengzw
 * @date 2017年1月22日
 */
public class PriorityQueueTest {

    
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue.add(2);
        queue.add(3);
        queue.add(1);
        queue.add(5);
        
        System.out.println(queue.poll()); //得到1
        
        PriorityQueue<String> queue1 = new PriorityQueue<String>();
        queue1.add("我");
        queue1.add("来");
        queue1.add("自");
        queue1.add("星");
        
        System.out.println(queue1.poll()); //得到1
        
    }
}
