/*
 * @Project Name: springmvcdemo
 * @File Name: TestingHashing.java
 * @Package Name: com.test.learn.consistenthashing
 * @Date: 2018年3月14日上午10:51:33
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.consistenthashing.c_hasing2;

/**
 * 
 * 这种情况下，要是删除服务器，或者减少服务器；缓存将大量的实效
 * 
 * 
 * @author zengzw-1220
 * @date 2018年3月14日上午10:51:33
 * @see
 */
public class TestingHashing {

	public static void main(String[] args) {

		Cluster cluster = createCluster();
		Entry[] entries = {
				new Entry("i"),
				new Entry("have"),
				new Entry("a"),
				new Entry("pen"),
				new Entry("an"),
				new Entry("apple"),
				new Entry("applepen"),
				new Entry("pineapple"),
				new Entry("pineapplepen"),
				new Entry("PPAP")
		};

		for(Entry e:entries){
			cluster.put(e);
		}
		
		//模拟添加服务器
		cluster.addServer(new Server("192.168.0.6"));
		
		
		findEntries(cluster, entries);

	}

	
	/*
	 * 创建服务器
	 */
	private static Cluster createCluster() {
		Cluster c = new Cluster();
		c.addServer(new Server("192.168.0.0"));
		c.addServer(new Server("192.168.0.1"));
		c.addServer(new Server("192.168.0.2"));
		c.addServer(new Server("192.168.0.3"));
		c.addServer(new Server("192.168.0.4"));
		c.addServer(new Server("192.168.0.5"));
		return c;
	}

	
	/*
	 * 查找缓存
	 */
	private static void findEntries(Cluster c, Entry[] entries) {
		for (Entry e : entries) {
			if (c.get(e) == e) {
				System.out.println("重新找到了entry:" + e);
			} else {
				System.out.println("entry已失效:" + e);
			}
		}
	}
}
