/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.concurrent.queue.delaytest;

import java.util.concurrent.DelayQueue;

/**
 *
 * @author zengzw
 * @date 2017年1月23日
 */
public class WangBa implements Runnable{

    public boolean yinye =  true;

    private DelayQueue<WangMing> queue = new DelayQueue<WangMing>();

    
    /**
     * 上机操作
     * 
     * @param name
     * @param id
     * @param money
     */
    public void shangji(String name,String id,int money){
        WangMing wm = new WangMing(name, id, 1000*60*money+System.currentTimeMillis());
        System.out.println("网名："+wm.getName() +" 省份证:"+wm.getId() +" 交钱："+money +" 块，开始上机了......");
        
        this.queue.add(wm);
    }
    
    
    
    
    
    @Override
    public void run() {
        while(yinye){
            try {
                System.out.println("check ing");
                WangMing wm = queue.take();

                System.out.println("网名："+wm.getName() +" 省份证:"+wm.getId() +" 时间到了，下机");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        System.out.println("网吧开始营业了....");
        WangBa wangBa = new WangBa();
        Thread thread = new Thread(wangBa);
        thread.start();
        
        wangBa.shangji("Lucy", "444444444444",2);
        wangBa.shangji("Tom", "5555555555555",5);
        wangBa.shangji("Jim", "111111111111",1);
        
    }

    
    
    
}
