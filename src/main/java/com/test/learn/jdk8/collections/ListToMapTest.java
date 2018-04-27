/*
 * @Project Name: springmvcdemo
 * @File Name: ListToMapTest.java
 * @Package Name: com.test.learn.jdk8.collections
 * @Date: 2018年1月31日上午10:27:23
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.test.learn.jdk8.vo.Hosting;

/**
 * List 转Map
 * 
 * @author zengzw-1220
 * @date 2018年1月31日上午10:27:23
 * @see
 */
public class ListToMapTest {

	public static void main(String[] args) {
		List<Hosting> list = new ArrayList<>();
		list.add(new Hosting(1, "liquidweb.com", 80000));
		list.add(new Hosting(2, "linode.com", 90000));
		list.add(new Hosting(3, "digitalocean.com", 120000));
		list.add(new Hosting(4, "aws.amazon.com", 200000));
		list.add(new Hosting(5, "mkyong.com", 1));

		Map<String, Long> map = list.stream()
				.collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites));

		System.out.println(map);

		Map<Integer,String> map1 = list.stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> x.getName()));

		System.out.println(map1);

		/**
		 * 重复键 
		 */
		list.add(new Hosting(6, "linode.com", 10000));

		// 报错：Duplicate key linode.com
		/*Map<String,String> map2 = list.stream()
				.collect(Collectors.toMap(x -> x.getName(), x -> x.getName()));*/

		/*
		 * (oldValue,newValue)->oldValue 键重复的情况下，取老的数据
		 */
		Map<String, Long> map2 = list.stream()
				.collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites,(oldValue,newValue)->oldValue));
		System.out.println(map2);
		//取新的
		Map<String, Long> map3 = list.stream()
				.collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites,(oldValue,newValue)->newValue));
		System.out.println(map3);


		/*
		 * sort 根据webSize 排序，同时排除重复key，同时将数据加入到新对象LinkedHashMap中
		 */
		Map<String, Long> map4 = list.stream().sorted(Comparator.comparingLong(Hosting::getWebsites).reversed())
				.collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites,(oldValue,newValue)-> oldValue,LinkedHashMap::new));

		System.out.println(map4);

		groupbyList();
		
		
		distinct();
		
		nullAble();
	}

	public static void groupbyList(){
		List<Hosting> list = new ArrayList<>();
		list.add(new Hosting(1, "liquidweb.com", 80000));
		list.add(new Hosting(2, "liquidweb.com", 90000));
		list.add(new Hosting(3, "liquidweb.com", 120000));
		list.add(new Hosting(4, "aws.amazon.com", 200000));
		list.add(new Hosting(5, "mkyong.com", 1));
		

		Optional<List<Hosting>> optional  = Optional.ofNullable(list);
		List<Hosting> list2 = optional.map(p -> p).orElse(Collections.emptyList());
		System.out.println(list2);	


		Map<String, List<Hosting>> listResult = list.stream().collect(Collectors.groupingBy(Hosting::getName));
		listResult.forEach((k,v) -> {
			System.out.println("key:"+k);
			v.forEach(p ->{
				System.out.println(JSON.toJSONString(p));
			});
		});

		Map<String, List<Object>> listResult2 = list.stream().collect(Collectors.groupingBy(Hosting::getName,
				Collectors.mapping(p -> {
					if( p.getId() > 2){
						return 999L;
					}else{
						return 888L;
					}
				}, Collectors.toList())));
		System.out.println(listResult2);
	}
	
	public static void distinct(){
		System.out.println("---------distinct---------");
		List<Object> list = new ArrayList<>();
		list.add("aaa");
		list.add("aaa");
		list.add("cc");
		
		System.out.println(list.size());
		List<Object> listReuslt = list.stream().distinct().collect(Collectors.toList());
		
		System.out.println("----"+listReuslt.size());
		
		System.out.println("---list:"+list);
		int i = 0;
		list.stream().forEach(e ->{  
		    if(e.equals("c")){  
		        return;  
		    }  
		    System.out.println(">>>>>"+e);  
		}); 
	}
	
	public static void nullAble(){
		List<Object> list = null;
	System.out.println("----nullAble------");
		List<Object> list2 = Optional.ofNullable(list).filter(p -> p.equals("aaa")).get();
	}
}
