/*
 * @Project Name: springmvcdemo
 * @File Name: SortEntity.java
 * @Package Name: com.test.springmvc.sort
 * @Date: 2017年8月17日下午4:51:20
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年8月17日下午4:51:20
 * @see
 */
public class SortEntity {
	private int userId;

	private String userName;


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SortEntity(String name,int id){
		this.userName = name;
		this.userId = id;
	}


	public static void main(String[] args) {
		List<SortEntity> lstEntitys = new ArrayList<>();

		SortEntity entity = new SortEntity("userName0", 1);
		SortEntity entity1 = new SortEntity("userName1", 2);
		SortEntity entity2 = new SortEntity("userName2", 3);
		lstEntitys.add(entity);
		lstEntitys.add(entity2);
		lstEntitys.add(entity1);

		System.out.println(JSON.toJSONString(lstEntitys));
		
		Collections.sort(lstEntitys, new Comparator<SortEntity>(){

			@Override
			public int compare(SortEntity o1, SortEntity o2) {
				return o2.getUserId() - o1.getUserId();
			}
		});
		
		System.out.println(JSON.toJSONString(lstEntitys));

	}
}
