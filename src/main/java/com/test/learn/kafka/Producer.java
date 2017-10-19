/*
 * @Project Name: springmvcdemo
 * @File Name: Producer.java
 * @Package Name: com.test.learn.kafka
 * @Date: 2017年10月17日下午3:20:10
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 生成者
 * 
 * @author zengzw-1220
 * @date 2017年10月17日下午3:20:10
 * @see
 */
public class Producer extends Thread{


	private final KafkaProducer<Integer, String> producer;

	private final String topic;

	private final boolean isAsync;

	private final String serverIP = "192.168.48.128";

	public Producer(String topic, Boolean isAsync)
	{
		Properties props = new Properties();
		props.put("bootstrap.servers", serverIP+":9092");
		props.put("client.id", "DemoProducer");
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<Integer, String>(props);
		this.topic = topic;
		this.isAsync = isAsync;
	}


	@Override
	public void run() {
		int messageNo = 1;

		while(true){
			String messageStr = "Message" + messageNo;
			long startTime = System.currentTimeMillis();
			if(isAsync){
				producer.send(new ProducerRecord<Integer, String>(topic, messageNo,messageStr),
						new MessageCallBack(startTime, messageNo, messageStr));
			}else{
				try {
					producer.send(new ProducerRecord<Integer, String>(topic, messageNo,messageStr)).get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}

			++messageNo;
		}

	}

	public static void main(String[] args) {

		boolean isAsync = true;
		Producer producerThread = new Producer("test", isAsync);
		producerThread.start();
	}
}
