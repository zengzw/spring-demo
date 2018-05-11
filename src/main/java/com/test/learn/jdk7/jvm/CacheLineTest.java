/*
 * @Project Name: springmvcdemo
 * @File Name: CacheLineTest.java
 * @Package Name: com.test.learn.jdk7.jvm
 * @Date: 2018年5月11日上午9:47:37
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7.jvm;

import fr.ujm.tse.lt2c.satin.cache.exceptions.CacheNotFoundException;
import fr.ujm.tse.lt2c.satin.cache.size.CacheInfo;
import fr.ujm.tse.lt2c.satin.cache.size.CacheLevel;
import fr.ujm.tse.lt2c.satin.cache.size.CacheType;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月11日上午9:47:37
 * @see
 */
public class CacheLineTest {
	
	public static void main(String[] args) throws CacheNotFoundException {
		CacheInfo cacheInfo = CacheInfo.getInstance();
		
		System.out.println(cacheInfo.getCacheLineSize(CacheLevel.L2,CacheType.DATA_CACHE));
	}
}
