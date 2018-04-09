/*
 * @Project Name: springmvcdemo
 * @File Name: LongEventHandler.java
 * @Package Name: com.test.learn.disruptor
 * @Date: 2018年3月27日下午3:37:06
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
 * @date 2018年3月27日下午3:37:06
 * @see
 */
public class LongEventHandler implements EventHandler<LongEvent>{

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {

		System.out.println("handle---"+event.get());
	}
}
