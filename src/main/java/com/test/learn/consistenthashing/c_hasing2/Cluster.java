/*
 * @Project Name: springmvcdemo
 * @File Name: Cluster.java
 * @Package Name: com.test.learn.consistenthashing
 * @Date: 2018年3月14日上午10:43:58
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.consistenthashing.c_hasing2;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性Hash
 * https://zh.wikipedia.org/wiki/%E4%B8%80%E8%87%B4%E5%93%88%E5%B8%8C
 * 
 * @author zengzw-1220
 * @date 2018年3月14日上午10:43:58
 * @see
 */
public class Cluster {

	private static final int SERVER_SIZE_MAX = 1024;
	
	private SortedMap<Integer, Server> servers = new TreeMap<Integer,Server>();

	public static int size = 0;

	public void put(Entry e){
		int indiex = e.hashCode() % size;
		System.out.println("put>>name:"+e.getKey() +";HashCode:"+e.hashCode());
		routeServer(indiex).put(e);
	}

	
	
	public Entry get(Entry e){
		int index = e.hashCode() % size;

		System.out.println("get>> name:"+e.getKey() +";HashCode:"+e.hashCode());

		return routeServer(index).get(e);
	}


	/**
	 * 上架服务器
	 * 
	 * 
	 * @date 2018年3月14日上午11:01:47
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param server
	 * @return
	 */
	public boolean addServer(Server server){
		if(size > SERVER_SIZE_MAX){
			return false;
		}

		servers.put(server.hashCode(),server);
		size++;
		
		return true;
	}

	
	public Server routeServer(int hash){
		if(servers.isEmpty()){
			return null;
		}
		
		if(!servers.containsKey(hash)){
			SortedMap<Integer, Server> tailMap = servers.tailMap(hash);
			
			if(tailMap.isEmpty()){
				hash = servers.firstKey();
			}else{
				hash = tailMap.firstKey();
			}
		}
		
		return servers.get(hash);
	}
	
}

