/*
 * @Project Name: springmvcdemo
 * @File Name: LoadingCacheTest.java
 * @Package Name: com.test.learn.google
 * @Date: 2017年12月4日下午6:05:38
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.google;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 接口时间窗口的限流
 * 
 * 1秒钟不能超出1000个
 * @author zengzw-1220
 * @date 2017年12月4日下午6:05:38
 * @see
 */
public class LoadingCacheTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		LoadingCache<Long,AtomicLong> counter = CacheBuilder.newBuilder()
				.expireAfterAccess(2, TimeUnit.SECONDS)
				.build(new CacheLoader<Long,AtomicLong>(){

					@Override
					public AtomicLong load(Long key) throws Exception {

						return new AtomicLong(0);
					}});

		long limit = 1000;
		
		while(true){
			long currentTime = System.currentTimeMillis() / 1000;
			if(counter.get(currentTime).incrementAndGet() > limit){
				System.out.println("限流了："+currentTime);
				Thread.sleep(200);
				continue;
			}else{
				System.out.println("--------===========");
			}
		}
		
	}
}
