/*
 * @Project Name: springmvcdemo
 * @File Name: NIOClient2.java
 * @Package Name: com.test.learn.nio.client
 * @Date: 2017年9月30日下午2:39:55
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Executors;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月30日下午2:39:55
 * @see
 */
public class NIOClient2 implements Runnable{

	private Selector selector;


	public NIOClient2(Selector selector) {
		this.selector = selector;
	}
	
	
	@Override
	public void run() {
		try {
			
			// select()方法只能使用一次，用了之后就会自动删除,每个连接到服务器的选择器都是独立的
			while(selector.select() > 0){
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					
					if(key.isReadable()){
						SocketChannel  channel = (SocketChannel)key.channel();
						
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						if(channel.read(buffer) != -1){
							buffer.flip();
							byte[] bytes = new byte[buffer.limit()];
							int i = 0;
							while(buffer.hasRemaining()){
								bytes[i] = buffer.get();
								i++;
							}
							
							System.out.println("服务端-返回消息："+new String(bytes));
							
						}else{
							channel.close();
						}
					}
					
					iterator.remove();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
	}
	

	public static void main(String[] args) throws IOException {

		Selector selector = null;
		SocketChannel channel = null;
		Scanner scanner = null;


		try {
			selector = selector.open(); //创建selector
			channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8080)); //链接服务器
			channel.configureBlocking(false);


			//注册事件
			channel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);


			System.out.println("Connection setting up.....");

			while(!channel.isConnected()){
				System.out.print(".");
				Thread.sleep(1000);
			}

			System.out.println();


			//给服务器发送消息
			channel.write(ByteBuffer.wrap("client startup......".getBytes()));

			//用另外一个线程去监听处理服务端发送的消息
			Executors.newFixedThreadPool(1).submit(new NIOClient2(selector));

			//不断控制台输入，给服务器发送哦消息
			while(true){
				scanner = new Scanner(System.in);
				channel.write(ByteBuffer.wrap(scanner.next().getBytes()));
			}
		} catch (Exception e) {

			e.printStackTrace();

		}finally {
			if (scanner != null) {
				scanner.close();
			}
			if (channel != null) {
				channel.close();
			}
			if (selector != null) {
				selector.close();
			}
		}
	}
}
