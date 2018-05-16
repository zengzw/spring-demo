/*
 * @Project Name: springmvcdemo
 * @File Name: HelloList.java
 * @Package Name: com.test.learn.calendar
 * @Date: 2018年4月9日上午10:04:20
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk.calendar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.test.springmvc.model.User;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月9日上午10:04:20
 * @see
 */
public class HelloList {
	
	public static void main(String[] args) {

		List<User> lsitUser = new ArrayList<>();
		
		User user = new User();
		user.setId(1);
		user.setName("name");
		User user2 = new User();
		user2.setId(12);
		user2.setName("name12");
		lsitUser.add(user);
		lsitUser.add(user2);
		
		User userResult = null;
		for(int i = 0; i< lsitUser.size(); i++){
			userResult = lsitUser.get(i);
			userResult.setName("update-name"+i);
		}
		
	 lsitUser.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
		
		System.out.println(JSON.toJSONString(lsitUser));
	}
}
