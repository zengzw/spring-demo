/*
 * @Project Name: springmvcdemo
 * @File Name: AC.java
 * @Package Name: com.test.springmvc
 * @Date: 2017年11月4日下午12:03:53
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月4日下午12:03:53
 * @see
 */
public class AC implements Filter{
	
	public static void main(String[] args) {

		System.out.println(2 << 3);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {

		// TODO Auto-generated method stub
		
	}
}
