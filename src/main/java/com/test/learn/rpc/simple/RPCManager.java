/*
 * @Project Name: springmvcdemo
 * @File Name: RPCManager.java
 * @Package Name: com.test.learn.rpc.simple
 * @Date: 2018年3月11日下午4:51:41
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.rpc.simple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月11日下午4:51:41
 * @see
 */
public class RPCManager {

	public static ConcurrentMap<String, Object> interfacelReferenceMap = new ConcurrentHashMap<String, Object>();


	/**
	 * 
	 * 绑定服务
	 * 
	 * @date 2018年3月12日下午2:37:50
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param services
	 */
	public static void initBind(List<Object> services){
		for(Object service:services){
			System.out.println("==bind service:service name:"+service.getClass().getName());
			System.out.println("==bind service interface name:"+service.getClass().getInterfaces()[0].getName());

			interfacelReferenceMap.put(service.getClass().getInterfaces()[0].getName(), service);
		}
		
		System.out.println("----bind service success");
	}


	/**
	 * 
	 * 查找服务
	 * 
	 * @date 2018年3月12日下午2:38:05
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param serviceName
	 * @return
	 */
	public static Object findService(String serviceName){
		System.out.println("---"+interfacelReferenceMap);

		Object object = interfacelReferenceMap.get(serviceName);
		if(object == null){
			throw new RuntimeException(serviceName+"，没有注册");
		}
		return object;
	}


	
	/**
	 * 
	 * 启动，监听服务
	 * 
	 * @date 2018年3月12日下午2:38:15
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param port
	 * @throws IOException
	 */
	public static void  startService(int port) throws IOException{

		System.out.println("start service  on :"+port);

		ServerSocket serverSocket = new ServerSocket(port);
		while(true){
			final Socket socket = serverSocket.accept();
			new Thread(new Runnable() {

				@Override
				public void run() {

					ObjectInputStream inputStream  = null;
					ObjectOutputStream outputStream = null;
					try {
						inputStream = new ObjectInputStream(socket.getInputStream());

						//接受客户端穿过来的参数，服务名，方法名，请求参数类型，参数列表
						String serviceName = inputStream.readUTF();
						String methodName = inputStream.readUTF();
						Class<?>[] parameterTypes = (Class<?>[])inputStream.readObject();
						Object[] arguments = (Object[])inputStream.readObject();

						//根据服务名，查找服务名称
						Object service = findService(serviceName);

						//执行服务，给客户端响应
						outputStream = new ObjectOutputStream(socket.getOutputStream());
						Method method = service.getClass().getMethod(methodName, parameterTypes);
						Object result = method.invoke(service, arguments);
						outputStream.writeObject(result);

					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}finally{
						if(inputStream != null){
							try {
								inputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(outputStream != null){
							try {
								outputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				}
			}).start();
		}
	}



	/**
	 * 
	 * 引用服务端的服务，通过代理类来实现调用，响应
	 * 
	 * @date 2018年3月12日上午10:12:40
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param interfaceClass
	 * @param host
	 * @param port
	 * @return
	 */
	@SuppressWarnings({ "hiding", "unchecked" })
	public static <T> T refer(final Class<T> interfaceClass,final String host,final int port){

		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},new InvocationHandler() {

			@SuppressWarnings("resource")
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				Socket socket = new Socket(host, port);
				Object result = null;
				ObjectOutputStream outputStream = null;
				ObjectInputStream inputStream = null;
				try{
					/**
					 * 向服务端，传参数
					 */
					outputStream = new ObjectOutputStream(socket.getOutputStream());
					outputStream.writeUTF(interfaceClass.getName());
					System.out.println(interfaceClass.getName());
					outputStream.writeUTF(method.getName());//
					outputStream.writeObject(method.getParameterTypes());
					outputStream.writeObject(args);

					/**
					 * 接受服务端的响应
					 */
					inputStream = new ObjectInputStream(socket.getInputStream());
					result = inputStream.readObject();
					if(inputStream != null){
						inputStream.close();
					}
				}finally{
					if(outputStream != null){
						outputStream.close();
					}

					if(inputStream != null){
						inputStream.close();
					}
				}

				return result;
			}
		});
	}

}
