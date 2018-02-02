/*
 * @Project Name: springmvcdemo
 * @File Name: GuavaCacheTest.java
 * @Package Name: com.test.learn.google
 * @Date: 2018年1月22日上午11:37:48
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.google;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月22日上午11:37:48
 * @see
 */
public class GuavaCacheTest {

	public static void main(String[] args) throws ExecutionException {
		Cache<String, String> mCache = CacheBuilder.newBuilder()
				.concurrencyLevel(4) //值越大，并发能力越强
				//缓存数据在给定的时间内没有写（创建，覆盖），则被回收，既定期会回收数据
				.expireAfterAccess(10, TimeUnit.SECONDS) 
				//缓存的容量，当超出maximumsize时，按照LRU进行缓存回收。
				.maximumSize(10000)
				.recordStats()
				.build();

		mCache.put("a", "acache");

		System.out.println(mCache.getIfPresent("a"));
		
		System.out.println(mCache.get("b",new Callable(){
			@Override
			public Object call() throws Exception {
				System.out.println("---bcall---");
				return "b-call";
			}

		}));

	}
}
