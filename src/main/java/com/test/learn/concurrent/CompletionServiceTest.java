/*
 * @Project Name: springmvcdemo
 * @File Name: CompletionServiceTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年6月8日下午6:47:27
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月8日下午6:47:27
 * @see
 */
public class CompletionServiceTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		 // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(12);
        
        /**
         * 而CompletionService的实现是维护一个保存Future对象的BlockingQueue。只有当这个Future对象状态是结束的时候，才会加入到这个Queue中，take()方法其实就是Producer-Consumer中的Consumer。它会从Queue中取出Future对象，如果Queue是空的，就会阻塞在那里，直到有完成的Future对象加入到Queue中。
       CompletionService采取的是BlockingQueue<Future<V>>无界队列来管理Future。则有一个线程执行完毕把返回结果放到BlockingQueue<Future<V>>里面。就可以通过completionServcie.take().get()取出结果。
         */
        CompletionService<String>  cs = new ExecutorCompletionService<>(pool);
        
        for(int i = 0; i<10; i++){
        	cs.submit(new Callable(){

    			@Override
    			public Object call() throws Exception {

    				return "call result";
    			}
    			
    		});
        }
        System.out.println("0------");
        
        
        //取出結果
        for(int i = 0; i<10; i++){
        	System.out.println(cs.take().get());
        }
        
        pool.shutdown();
	}
}
