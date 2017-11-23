/*
 * @Project Name: springmvcdemo
 * @File Name: TransactionProxy.java
 * @Package Name: com.test.learn.proxytransaction
 * @Date: 2017年11月23日上午10:41:23
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxytransaction;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 模拟事务代理
 * 
 * 
 * 通过CGLIB为类创建代理
 * ..
 * 
 * 
 * @author zengzw-1220
 * @date 2017年11月23日上午10:41:23
 * @see
 */
public class TransactionProxy implements MethodInterceptor{

	private static TransactionProxy instance = new TransactionProxy();

	public TransactionProxy() {	}

	public static TransactionProxy getInstance(){
		return instance;
	}


	public <T> T getProxy(Class<T> clz){
		return (T)Enhancer.create(clz, this);
	}





	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

		Object result = null;

		/*
		 * 如果开起了Transaction 那么用事物的方式来处理，否则正常执行
		 */
		if(method.isAnnotationPresent(Transaction.class)){
			try{
				System.out.println("-----开起事物处理------");

				//执行操作
				method.setAccessible(true);
				result = proxy.invokeSuper(obj, args);

				System.out.println("----提交事物------");

			}catch(Exception e){
				System.out.println("出错了，事物回滚。。。。。");
				e.printStackTrace();	
			}
		}else{
			result = proxy.invokeSuper(obj, args);
			System.out.println("没有事务的操作。");
		}

		return result;
	}

}
