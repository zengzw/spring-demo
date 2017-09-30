/*
 * @Project Name: springmvcdemo
 * @File Name: NIOClient.java
 * @Package Name: com.test.learn.nio.client
 * @Date: 2017年9月30日下午12:13:13
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年9月30日下午12:13:13
 * @see
 */
public class NIOClient {
	
	public static void main(String[] args) throws IOException {

		 //创建连接的地址  
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8080); 
          //声明连接通道  
        SocketChannel channel = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        try {
        	//打开通道
        	channel = SocketChannel.open();
        	
        	//链接
        	channel.connect(address);
        	
        	
        	while(true){
        		byte[] bytes = new byte[1024];
        		System.in.read(bytes);
        		
        		buffer.put(bytes);  //把数据放到缓冲区中  
        		buffer.flip(); //对缓冲区进行复位  
        		channel.write(buffer);//写出数据  
        		buffer.clear(); //清空缓冲区数据  
        	}
        	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(channel != null){
				channel.close();
			}
		}
	}
}
