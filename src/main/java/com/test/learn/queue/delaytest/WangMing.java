/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.queue.delaytest;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zengzw
 * @date 2017年1月23日
 */
public class WangMing implements Delayed{

    /**
     * 用户名
     */
    private String name;


    /**
     * 用于身份证ID
     */
    private String id;


    /**
     * 截止时间
     */
    private long endTime;


    public WangMing(String name,String id,long endTime){
        this.name = name;
        this.id =  id;
        this.endTime = endTime;
    }


    @Override
    public int compareTo(Delayed o) {
//        System.out.println("------compareTo");
        WangMing wangMing = (WangMing)o;
        long end = endTime - wangMing.endTime;

        if(end > 0 ){
            return 1;
        }
        
        if(end < 0 ){
            return -1;
        }

        return 0;
    }


    
    /** 
     * 用来判断是否到了截止时间 
     */  
    @Override
    public long getDelay(TimeUnit unit) {
        long currentTime =  endTime -  System.currentTimeMillis();
//        System.out.println("-----getDelay:"+currentTime);
        
        return currentTime;
    }


    public long getEndTime() {
        return endTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
