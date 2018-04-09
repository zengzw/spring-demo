/*
 * @Project Name: springmvcdemo
 * @File Name: LongEventFactory.java
 * @Package Name: com.test.learn.disruptor
 * @Date: 2018年3月27日下午3:36:14
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月27日下午3:36:14
 * @see
 */
public class LongEventFactory implements EventFactory<LongEvent>{

	@Override
	public LongEvent newInstance() {

		return new LongEvent();
	}
}
