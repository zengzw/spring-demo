/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.zk.config;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


/**
 *
 * @author zengzw
 * @date 2016年9月23日
 */
public class WatchConfig implements Watcher{


    private static String ZK_URL = "172.16.1.190:2181" ;
    private static int timeout = 3000 ;

    private static String root="/myConf1";
    private static String urlNode =root+  "/url";
    private static String usernameNode =root+ "/username";
    private static String passwordNode =root+ "/password";

    public  String urlValue ="";
    public  String usernameValue ="/";
    public  String passwordValue ="/";

    ZooKeeper zkKeeper = null;
    public ZooKeeper initZK() throws IOException, InterruptedException, KeeperException{
        zkKeeper= new ZooKeeper(ZK_URL, timeout,this);
        while(ZooKeeper.States.CONNECTED != zkKeeper.getState()){
            Thread.sleep(3000);
        }
        
        zkKeeper.addAuthInfo("digest","test001:test001".getBytes());

        zkKeeper.getChildren(root, new nodeWatch());
        return zkKeeper;

    }

 class nodeWatch implements Watcher{
     @Override
     public void process(WatchedEvent event) {
         System.out.println("<<<<<<<----------"+event);
         if(event.getType() == Watcher.Event.EventType.NodeChildrenChanged){
             try {
                List<String> ss = zkKeeper.getChildren(root, this);
                ListIterator<String> listIterator = ss.listIterator();
                System.out.println("result:"+ss);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
         }
     }
 }

    public void initValue() throws KeeperException, InterruptedException, IOException{
        if(zkKeeper != null){
            urlValue = new String(zkKeeper.getData(urlNode, true,null));
            usernameValue = new String(zkKeeper.getData(usernameNode, true,null));
            passwordValue = new String(zkKeeper.getData(passwordNode, true,null));
        }
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println("----------"+event);
        if(event.getType() == Watcher.Event.EventType.None){
            System.out.println("--------链接成功");
        }else if(event.getType() == Watcher.Event.EventType.NodeCreated){
            System.out.println("--------节点创建");
        }else if(event.getType() == Watcher.Event.EventType.NodeDataChanged){
            try {
                initValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("--------节点数据改变");
        }else if(event.getType() == Watcher.Event.EventType.NodeChildrenChanged){
            try {
                initValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("--------子节点数据变更");
        }else if(event.getType() == Watcher.Event.EventType.NodeDeleted){
            System.out.println("--------节点删除");

        }

    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        WatchConfig config = new WatchConfig();
        config.initZK();
        config.initValue();

        while(true){
            System.out.println(config.getUrlValue());
            System.out.println(config.getUsernameValue());
            System.out.println(config.getPasswordValue());

            Thread.sleep(10000);
        }
    }


    public static String getZK_URL() {
        return ZK_URL;
    }


    public static void setZK_URL(String zK_URL) {
        ZK_URL = zK_URL;
    }


    public static int getTimeout() {
        return timeout;
    }


    public static void setTimeout(int timeout) {
        WatchConfig.timeout = timeout;
    }


    public static String getRoot() {
        return root;
    }


    public static void setRoot(String root) {
        WatchConfig.root = root;
    }


    public static String getUrlNode() {
        return urlNode;
    }


    public static void setUrlNode(String urlNode) {
        WatchConfig.urlNode = urlNode;
    }


    public static String getUsernameNode() {
        return usernameNode;
    }


    public static void setUsernameNode(String usernameNode) {
        WatchConfig.usernameNode = usernameNode;
    }


    public static String getPasswordNode() {
        return passwordNode;
    }


    public static void setPasswordNode(String passwordNode) {
        WatchConfig.passwordNode = passwordNode;
    }


    public String getUrlValue() {
        return urlValue;
    }


    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }


    public String getUsernameValue() {
        return usernameValue;
    }


    public void setUsernameValue(String usernameValue) {
        this.usernameValue = usernameValue;
    }


    public String getPasswordValue() {
        return passwordValue;
    }


    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }


}
