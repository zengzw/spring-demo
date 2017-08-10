/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.mthread.guava;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 *我们使用Guava的Cache来存储计数器，过期时间设置为2秒（保证1秒内的计数器是有的），然后我们获取当前时间戳然后取秒数来作为KEY进行计数统计和限流，这种方式也是简单粗暴，刚才说的场景够用了。
 *
 * @author zengzw
 * @date 2017年7月21日
 */
public class LoadingCacheTest {

   static LoadingCache<String, Integer> counter =
            CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Integer>() {

                @Override
                public Integer load(String key) throws Exception {
                    System.out.println("-0--");
                    return getRandom();
                }
            });
   
   
    
    public static void main(String[] args) throws ExecutionException {
  
        for(int i = 0; i <1000; i++){
            System.out.println(test2().get("a"));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    public static void test1() throws ExecutionException{
        LoadingCache<Long, AtomicLong> counter =
                CacheBuilder.newBuilder()
                        .expireAfterWrite(2, TimeUnit.SECONDS)
                        .build(new CacheLoader<Long, AtomicLong>() {
                            @Override
                            public AtomicLong load(Long seconds) throws Exception {
                                System.out.println("----new AtomicLong");
                                return new AtomicLong(0);
                            }
                        });
        long limit = 10;
        while(true) {
            //得到当前秒
            long currentSeconds = System.currentTimeMillis() / 1000;
            long i = counter.get(currentSeconds).incrementAndGet();
            System.out.println("------------------------------"+i);
            if(i > limit) {
                System.out.println("限流了:" + currentSeconds);
                continue;
            }else{
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
            //业务处理
         
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static LoadingCache test2() throws ExecutionException{
        return counter;
    }
    
    
    public static Integer getRandom(){
        return new Random().nextInt(1000);
    }
}
