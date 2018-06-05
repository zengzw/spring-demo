/*
 * @Project Name: springmvcdemo
 * @File Name: CusSocketServer.java
 * @Package Name: com.test.learn.socket
 * @Date: 2018年6月4日上午10:52:28
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * TODO
 * @author zengzw-1220
 * @date 2018年6月4日上午10:52:28
 * @see
 */
public class CusSocketServer {
	public static final int PORT = 12345;//监听的端口号     

	ExecutorService executor = Executors.newFixedThreadPool(20);//线程池


	public static void main(String[] args) {    
		System.out.println("服务器启动...\n");    
		CusSocketServer server = new CusSocketServer();    
		server.init();    
	}    

	public void init(){
		try{
			ServerSocket serverSocket = new ServerSocket(PORT);

			while(true){
				Socket client = serverSocket.accept();
				System.out.println("@server accept....");
				executor.submit(new SocketHandler(client));

			}

		}catch(Exception exception){
			exception.printStackTrace();
		}finally {

		}
	}

	/**
	 * 
	 * 因为socket 是阻塞的，一个线要等待另外一个线程处理完
	 * 
	 * 所以，用多线程来处理线程的
	 * 
	 * @author zengzw-1220
	 * @date 2018年6月4日上午11:05:32
	 */
	private class SocketHandler implements Runnable{
		private Socket socket;


		public SocketHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("threa run...");
			try{
				while(!Thread.currentThread().isInterrupted() && !socket.isClosed()){
					//读取客户端的数据,阻塞，直到有数据进来
					DataInputStream in = new DataInputStream(socket.getInputStream());
					////这里要注意和客户端输出流的写方法对应,否则会抛 EOFException  
					String result = in.readUTF();
					System.out.println("server:read client data :"+result);
					

					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF("server receive data:"+result);

					out.flush();
					out.close();
					in.close();
//					Thread.sleep(2000);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (socket != null) {    
					try {    
						socket.close();    
					} catch (Exception e) {    
						socket = null;    
						System.out.println("服务端 finally 异常:" + e.getMessage());    
					}    
				}    
			}
		}


	}
}
