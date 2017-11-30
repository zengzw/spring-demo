/*
 * @Project Name: springmvcdemo
 * @File Name: ClassUtils.java
 * @Package Name: com.test.learn.classutils
 * @Date: 2017年11月24日上午11:46:23
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.classutils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import com.ctc.wstx.util.StringUtil;
import com.test.RpcBootStrap;
import com.test.base.exception.BusinessRuntimeException;
import com.test.springmvc.utils.StringUtils;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月24日上午11:46:23
 * @see
 */
public class ClassUtils {

	private ClassUtils(){}


	/**
	 *
	 * 获取类加载器
	 * @return
	 */
	public static  ClassLoader getClassloader(){
		return Thread.currentThread().getContextClassLoader();
	}

	/**
	 * 
	 * @return
	 */
	public static String getClassPath(){
		URL resource = getClassloader().getResource("");
		if(resource == null){
			return "";
		}

		return resource.getPath();
	}

	
	/*
	 * 加载类，自动初始化
	 */
	public static Class<?> loadClass(String className){
		
		if(StringUtils.isEmpty(className)){
			throw new BusinessRuntimeException("", "class name is null");
		}
		
		return loadClass(className, true);
	}
	
	
	/*
	 * 初始化类
	 */
	public static Class<?> loadClass(String className,boolean initialize){
		Class<?> clz = null;
		try {
			clz = Class.forName(className, initialize, getClassloader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return clz;
	}

	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
		/*Class<RpcBootStrap> clz  = (Class<RpcBootStrap>) loadClass("com.test.RpcBootStrap");
		RpcBootStrap rpcBootStrap = clz.newInstance();
		System.out.println(rpcBootStrap);*/
		
		  Enumeration<URL> urls = getClassloader().getResources("com/test/base");
		  System.out.println(urls);
		  while(urls.hasMoreElements()){
			  URL url = urls.nextElement();
			  System.out.println(url.getProtocol() +"    "+url.getFile());
		  }
	}


}
