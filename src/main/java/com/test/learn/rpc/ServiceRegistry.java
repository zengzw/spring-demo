/*
 * @Project Name: springmvcdemo
 * @File Name: ServiceRegistry.java
 * @Package Name: com.test.learn.rpc
 * @Date: 2017年9月1日下午5:33:30
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.learn.rpc.contants.ZKContants;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月1日下午5:33:30
 * @see
 */
public class ServiceRegistry {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);
	 
	 private String registryAddress;
	 
	 
	public ServiceRegistry(String address) {
		this.registryAddress = address;
	}
	 
	 private CountDownLatch latch =  new CountDownLatch(1);
	 
	 
	 public void register(String data){
		 if(StringUtils.isNoneBlank(data)){
			 try {
				//
				ZooKeeper zooKeeper = connectZK();
				
				if(zooKeeper != null){
					createNote(zooKeeper, data);
				}
				
			} catch (IOException | InterruptedException e) {
			
				e.printStackTrace();
			}
			 
		 }
		 
	 }
	 
	 
	 
	 /**
	  *  zookeper
	  *  
	  * 
	  * @date 2017年9月1日下午6:10:38
	  * @author zengzw-1220
	  * @since 1.0.0 
	  * @return
	  * @throws IOException
	  * @throws InterruptedException
	  */
	 private ZooKeeper connectZK() throws IOException, InterruptedException{
		 ZooKeeper zk = null;
		 
		 zk = new ZooKeeper(registryAddress, ZKContants.ZK_SESSION_TIMEOUT, new Watcher(){

			@Override
			public void process(WatchedEvent event) {
				if(event.getState() == Event.KeeperState.SyncConnected){
					latch.countDown();
				}
			}
			 
		 });
		 latch.await();
		 
		 return zk;
	 }
	 
	 
	 private void createNote(ZooKeeper zk,String data){
		 try {
			zk.create(ZKContants.ZK_DATA_PATH, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	 }
}
