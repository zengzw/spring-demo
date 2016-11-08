/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.zk.config;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 *
 * @author zengzw
 * @date 2016年9月22日
 */
public class SetConfig {

    private static String ZK_URL = "172.16.1.190:2181" ;
    private static int timeout = 3000 ;
    
    private static String root="/myConf";
    private static String urlNode =root+  "/url";
    private static String usernameNode =root+ "/username";
    private static String passwordNode =root+ "/password";
    
    private static String urlValue ="url22222";
    private static String usernameValue ="/username2222";
    private static String passwordValue ="/password2222";

    public static void main(String[] args) throws InterruptedException, KeeperException {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZK_URL, timeout, new Watcher(){

                @Override
                public void process(WatchedEvent event) {
                    System.out.println("----事件触发:"+event);
                }
                
            });
            
            while(ZooKeeper.States.CONNECTED != zooKeeper.getState()){
                Thread.sleep(3000);
            }
            
            //验证
           zooKeeper.addAuthInfo("digest","test001".getBytes());
            
            //创建节点，数据
            if(zooKeeper.exists(root, true) == null){
                zooKeeper.create(root, "root".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            }
            if(zooKeeper.exists(urlNode, true) == null){
                zooKeeper.create(urlNode, urlValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            }
            if(zooKeeper.exists(usernameNode, true) == null){
                zooKeeper.create(usernameNode,usernameValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            }
            if(zooKeeper.exists(passwordNode, true) == null){
                zooKeeper.create(passwordNode, passwordValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            }
           
            zooKeeper.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
