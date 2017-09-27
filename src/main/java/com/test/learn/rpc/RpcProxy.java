/*
 * @Project Name: springmvcdemo
 * @File Name: RPXProxy.java
 * @Package Name: com.test.learn.rpc
 * @Date: 2017年9月26日上午9:48:07
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import com.test.learn.rpc.vo.RpcRequest;
import com.test.learn.rpc.vo.RpcResponse;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月26日上午9:48:07
 * @see
 */
public class RpcProxy {

	private String serverAddress;


	private ServiceDiscovery discovery;


	public RpcProxy(ServiceDiscovery discovery){
		this.discovery = discovery;
	}



	@SuppressWarnings("unchecked")
	public <T> T create(Class<?> interfaceClass){

		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {


			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				RpcRequest request = new RpcRequest();
				request.setRequestId(UUID.randomUUID().toString());
				request.setClassName(method.getDeclaringClass().getName());
				request.setMethodName(method.getName());
				request.setParameterTypes(method.getParameterTypes());
				request.setParameters(args);

				if(discovery != null){
					serverAddress = discovery.discover(); //发现服务
				}

				System.out.println("------>connect address:"+serverAddress);

				String[] address = serverAddress.split(":");
				String host = address[0];
				int port = Integer.parseInt(address[1]);

				//调用客户端，发布服务
				RpcClient client = new RpcClient(host, port);
				RpcResponse response = client.send(request);


				if(response.getError() != null){
					return response.getError();
				}

				return response.getResult();

			}
		});

	}
}
