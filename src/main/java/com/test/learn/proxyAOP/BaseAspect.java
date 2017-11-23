/*
 * @Project Name: springmvcdemo
 * @File Name: BaseAspect.java
 * @Package Name: com.test.learn.proxyAOP
 * @Date: 2017年11月23日下午3:55:38
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.proxyAOP;

import java.lang.reflect.Method;

import org.apache.commons.pool2.ObjectPool;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 切面类
 * 
 * @author zengzw-1220
 * @date 2017年11月23日下午3:55:38
 * @see
 */
public abstract class BaseAspect implements MethodInterceptor{


	public <T> T createProxy(Class<T> clz){
		return 	(T)Enhancer.create(clz, this);
	}


	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

		return advice(new PointCut(method, proxy), obj, args);
	}

	
	protected abstract Object advice(PointCut pointcut,Object proxy,Object[] args);
	

	class PointCut{
		private Method methodTarget;

		
		public Method getMethodTarget() {
		
			return methodTarget;
		}

		
		public void setMethodTarget(Method methodTarget) {
		
			this.methodTarget = methodTarget;
		}

		
		public MethodProxy getMethodProxy() {
		
			return methodProxy;
		}

		
		public void setMethodProxy(MethodProxy methodProxy) {
		
			this.methodProxy = methodProxy;
		}

		private MethodProxy methodProxy;


		public PointCut(Method target,MethodProxy proxy){
			this.methodProxy = proxy;
			this.methodTarget = target;
		}

		
		public Object invoke(Object proxy,Object[] 	args){
			Object result = null;

			try{
				result = methodProxy.invokeSuper(proxy, args);
				
			}catch(Exception exception){
				exception.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return result;
		}

	}
}
