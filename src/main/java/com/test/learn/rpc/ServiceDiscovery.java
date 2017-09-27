/*
 * @Project Name: springmvcdemo
 * @File Name: ServiceDiscovery.java
 * @Package Name: com.test.learn.rpc
 * @Date: 2017年9月21日下午6:31:47
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.storm.shade.org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.test.learn.rpc.contants.ZKContants;

/**
 * 实现服务发现	
 * @author zengzw-1220
 * @date 2017年9月21日下午6:31:47
 * @see
 */
public class ServiceDiscovery {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDiscovery.class);

	private CountDownLatch latch = new CountDownLatch(1);

	private volatile List<String> dataList = new ArrayList<>();

	private String registryAddress;



	public ServiceDiscovery(String registryAddress) {
		this.registryAddress = registryAddress;
		ZooKeeper zk = connectServer();
		if(zk != null){
			watchNode(zk);
		}
	}


	public String discover(){
		String data = null;
		if(!CollectionUtils.isEmpty(dataList)){
			if(dataList.size() == 1){
				data = dataList.get(0);
			}else{
				data = dataList.get(ThreadLocalRandom.current().nextInt(dataList.size()));
			}
		}
		
		return data;
	}


	public ZooKeeper connectServer(){
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(registryAddress, ZKContants.ZK_SESSION_TIMEOUT, new Watcher(){

				@Override
				public void process(WatchedEvent event) {
					if(event.getState() == Event.KeeperState.SyncConnected){
						latch.countDown();
					}
				}

			});

			latch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return zk;
	}


	private void watchNode(final ZooKeeper zk){
		try {
			List<String>  nodeList = zk.getChildren(ZKContants.ZK_REGISTRY_PATH, new Watcher(){

				@Override
				public void process(WatchedEvent event) {
					if(event.getType() == Event.EventType.NodeChildrenChanged){
						System.out.println("-----watchNode");
						watchNode(zk);
					}
				}

			});


			List<String> dataList = new ArrayList<>();
			for(String node : nodeList){
				byte[] bytes = zk.getData(ZKContants.ZK_REGISTRY_PATH +"/"+node, false, null);
				dataList.add(new String(bytes));
			}

			LOGGER.info(": dataList:{}",JSON.toJSONString(dataList));
			this.dataList = dataList;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
