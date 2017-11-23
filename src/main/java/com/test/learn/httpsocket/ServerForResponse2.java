/*
 * @Project Name: springmvcdemo
 * @File Name: ServerForResponse2.java
 * @Package Name: com.test.learn.httpsocket
 * @Date: 2017年11月21日上午11:38:32
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.httpsocket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月21日上午11:38:32
 * @see
 */
public class ServerForResponse2 {
	private ServerSocket server;

	public static void main(String[] args) {
		ServerForResponse2 server = new ServerForResponse2();
		server.start();
	}
	/**
	 * 启动方法
	 */
	public void start(){        
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 接收客户端
	 */
	private void receive(){
		try {
			Socket client =server.accept();

			byte[] date = new byte[20480];
			int len = client.getInputStream().read(date);

			//接收客户端的请求信息
			String requestInfo = new String(date,0,len).trim();     
			System.out.println(requestInfo);

			//响应
			Response rep = new Response(client.getOutputStream());
			rep.println("<html><head><title>HTTP响应示例</title>");
			rep.println("</head><body>Hello 你好!</body></html>");
			rep.pushToClient(200);

		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	/**
	 * 停止服务器
	 */
	public void stop(){
		
	}
}
