/*
 * @Project Name: springmvcdemo
 * @File Name: CusSocketClient.java
 * @Package Name: com.test.learn.socket
 * @Date: 2018年6月4日上午10:52:40
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月4日上午10:52:40
 * @see
 */
public class CusSocketClient {
	public static final String IP_ADDR = "localhost";//服务器地址   
	public static final int PORT = 12345;//服务器端口号    

	public static void main(String[] args) {

		Socket socket = null;
		try {
			int i = 0;
			while(!Thread.currentThread().isInterrupted()){
				socket = new Socket(IP_ADDR,PORT);
				//读取服务端的数据
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

				//向服务端发送数据
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				System.out.print("请输入: \t");    
				/*String str = new BufferedReader(new InputStreamReader(System.in)).readLine();   
				System.out.println("input:"+str);*/
				dataOutputStream.writeUTF("data"+i);

				String result = dataInputStream.readUTF();
				System.out.println("服务端返回的数据："+result);
				i++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("finally close");
			if (socket != null) {  
				try {  
					socket.close();  
				} catch (IOException e) {  
					socket = null;   
					System.out.println("客户端 finally 异常:" + e.getMessage());   
				}  
			}  
		}
	}
}
