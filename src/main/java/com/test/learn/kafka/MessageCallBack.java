/*
 * @Project Name: springmvcdemo
 * @File Name: MessageCallBack.java
 * @Package Name: com.test.learn.kafka
 * @Date: 2017年10月17日下午3:58:56
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年10月17日下午3:58:56
 * @see
 */
public class MessageCallBack implements Callback{

	private long startTime;
	private int key;

	private String message;


	public MessageCallBack(long startTime,int key,String message) {
		this.startTime = startTime;
		this.key = key;
		this.message = message;
	}

	@Override
	public void onCompletion(RecordMetadata metadata, Exception ex) {

		long elapsedTime = System.currentTimeMillis() - startTime;

		if(metadata != null){
			System.out.println(
					"message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
					"), " +
					"offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
		}else{
			ex.printStackTrace();
		}
	}
}
