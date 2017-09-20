/*
 * @Project Name: springmvcdemo
 * @File Name: RpcService.java
 * @Package Name: com.test.learn.rpc.anotation
 * @Date: 2017年9月1日下午5:34:25
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月1日下午5:34:25
 * @see
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component // 表明可被 Spring 扫描
public @interface RpcService {
	
	Class<?> value();
}
