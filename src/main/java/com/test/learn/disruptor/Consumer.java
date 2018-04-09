/*
 * @Project Name: springmvcdemo
 * @File Name: Consumer.java
 * @Package Name: com.test.learn.disruptor
 * @Date: 2018年3月27日下午3:25:55
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月27日下午3:25:55
 * @see
 */
public class Consumer implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		   long value = event.get();           
		
	}
}
