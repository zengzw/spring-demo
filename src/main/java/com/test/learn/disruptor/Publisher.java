/*
 * @Project Name: springmvcdemo
 * @File Name: Publisher.java
 * @Package Name: com.test.learn.disruptor
 * @Date: 2018年3月27日下午3:24:47
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * //pubisher逻辑，将原始数据转换为event，publish到ringbuffer
 * @author zengzw-1220
 * @date 2018年3月27日下午3:24:47
 * @see
 */
public class Publisher implements EventTranslatorOneArg<LongEvent, String>{

	@Override
	public void translateTo(LongEvent event, long sequence, String arg0) {
		event.set(Long.parseLong(arg0));
		
	}
}
