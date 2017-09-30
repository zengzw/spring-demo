/*
 * @Project Name: springmvcdemo
 * @File Name: NIOServer.java
 * @Package Name: com.test.learn.nio.server
 * @Date: 2017年9月30日上午11:25:09
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.ibatis.annotations.SelectKey;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月30日上午11:25:09
 * @see
 */
public class NIOServer {

	//通道管理器。相当于超级服务生，把门口迎接客人，服务客人的活都做了  
	private Selector selector;


	/*
	 * 获得一个ServerSocker通道，并对该通道做一切初始化工作。
	 * 
	 */
	public void initServer(int port) throws IOException{
		//获得一个ServerSocket通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); 
		//设置为不阻塞
		serverSocketChannel.configureBlocking(false);
		// 将该通道对应的ServerSocket绑定到port端口  
		serverSocketChannel.socket().bind(new InetSocketAddress(port));

		//获得一个通道管理器
		selector = Selector.open();

		//将通道管理器 和  该 通道 进行绑定。 并未该通道主持 SelectionKey.OP_ACCEPT事件。  
		// 事件注册后，当该事件到达时selector.select()会返回。若该事件没有到达selecter.select()会一直阻塞。
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

	}



	/*
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理 
	 */
	public void lister() throws IOException{

		System.out.println(" 服务启动完成......");

		//轮休访问selecter
		while(true){
			// 当注册的事件到达时，方法返回。否则,该方法会一直阻塞  
			selector.select();

			Iterator<SelectionKey> integer = this.selector.selectedKeys().iterator();
			while(integer.hasNext()){
				SelectionKey key = integer.next();
				// 删除已选的key,以防重复处理  
				integer.remove();

				handle(key);
			}

		}

	}


	/** 
	 * 处理请求 
	 * @throws IOException 
	 */  
	private void handle(SelectionKey key) throws IOException {

		//客户端请求链接
		if(key.isAcceptable()){
			handlerAccept(key);
		}
		else if(key.isReadable()){
			handlerReadler(key);
		}

	}


	/** 
	 * 处理连接请求 
	 * @throws IOException 
	 */  
	private void handlerAccept(SelectionKey key) throws IOException{
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		//获得客户端链接通道
		SocketChannel channel = server.accept();

		//设置成非阻塞
		channel.configureBlocking(false);

		System.out.println("新客户端链接....");
		// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道注册读的事件  
		channel.register(this.selector,SelectionKey.OP_READ);

	}


	/** 
	 * 处理读的事件 
	 * @throws IOException 
	 */  
	private void handlerReadler(SelectionKey key) throws IOException{
		  // 服务器可读取消息:得到事件发生的Socket通道  
		SocketChannel channel = (SocketChannel) key.channel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int read = channel.read(buffer);
		if(read > 0){
			byte[] data = buffer.array();
			String msg = new String(data);
			System.out.println("--服务端收到消息："+msg.trim());
			
			ByteBuffer outBuffer = ByteBuffer.wrap("收到！".getBytes());
			channel.write(outBuffer);
		}else{
			System.out.println("--客户端关闭");
			key.cancel();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.initServer(8080);
		server.lister();
		
	}
}
