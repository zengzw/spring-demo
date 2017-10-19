/*
 * @Project Name: springmvcdemo
 * @File Name: Customer.java
 * @Package Name: com.test.learn.kafka
 * @Date: 2017年10月17日下午4:10:13
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import kafka.utils.ShutdownableThread;

/**
 * 消费者
 * @author zengzw-1220
 * @date 2017年10月17日下午4:10:13
 * @see
 */
public class Consumer extends ShutdownableThread{
	
	private final KafkaConsumer<Integer, String> consumer;
	
	private final String topic;
	
	private final String serverIP = "192.168.48.128";
	
	
	  public Consumer(String topic)
	  {
	    super("KafkaConsumerExample", false);
	    Properties props = new Properties();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverIP+":9092");
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
	    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
	    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

	    consumer = new KafkaConsumer<>(props);
	    this.topic = topic;
	  }


	@Override
	public void doWork() {

		consumer.subscribe(Collections.singletonList(this.topic));
		ConsumerRecords<Integer, String> records = consumer.poll(1000);
		
		for(ConsumerRecord<Integer, String> record : records){
			 System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
		}
		
		
	}
	
	
	public static void main(String[] args) {

		String topic = "test";
		String logstashTopic = "logstashtest";
		
		//开启消费者线程后，会接收到之前生产者发送的消息
	    Consumer consumerThread = new Consumer(logstashTopic);
	    consumerThread.start();
	}
}
