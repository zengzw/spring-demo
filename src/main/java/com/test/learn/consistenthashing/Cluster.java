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

package com.test.learn.consistenthashing;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月14日上午10:43:58
 * @see
 */
public class Cluster {

	private static final int SERVER_SIZE_MAX = 1024;

	private Server[] servers = new Server[SERVER_SIZE_MAX];
	public static int size = 0;

	public void put(Entry e){
		int indiex = e.hashCode() % size;
		System.out.println("put>>name:"+e.getKey() +";HashCode:"+e.hashCode());
		servers[indiex].put(e);
	}

	public Entry get(Entry e){
		int index = e.hashCode() % size;

		System.out.println("get>> name:"+e.getKey() +";HashCode:"+e.hashCode());

		return servers[index].get(e);
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

		servers[size++]=server;
		return true;
	}

	
	/**
	 * 下架服务器
	 * 
	 * 
	 * @date 2018年3月14日上午11:01:35
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param index
	 * @return
	 */
	public boolean removeSever(int index){
		if(index-1 > size){
			return false;
		}

		servers[index]= null;
		size--;
		return true;
	}

	public static void main(String[] args) {



	}
}
