/*
 * @Project Name: springmvcdemo
 * @File Name: Transaction.java
 * @Package Name: com.test.learn.proxytransaction
 * @Date: 2017年11月23日上午11:15:36
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxytransaction;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月23日上午11:15:36
 * @see
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.PARAMETER})
public @interface Transaction {
}
