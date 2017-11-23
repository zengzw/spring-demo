/*
 * @Project Name: springmvcdemo
 * @File Name: ProductActionAspect.java
 * @Package Name: com.test.learn.proxyAOP
 * @Date: 2017年11月23日下午4:02:44
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxyAOP;


/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月23日下午4:02:44
 * @see
 */
public class ProductActionAspect extends BaseAspect{

	@Override
	protected Object advice(PointCut pointcut, Object proxy, Object[] args) {
		long begin = System.currentTimeMillis();
		
		Object result = pointcut.invoke(proxy, args);
		
        System.out.println("Time: " + (System.currentTimeMillis() - begin) + "ms");
        return result;
	}
}
