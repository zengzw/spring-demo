package com.test.learn.jdk;

import com.test.springmvc.model.User;


public class ClassInitTest {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		Class<?> clz = Class.forName("com.test.springmvc.model.User");
		User user = (User) clz.newInstance();
		System.out.println(user);
		
		User user2 = User.class.newInstance();
		System.out.println(user2);
		
		
		User user3 = new User();
		System.out.println(user3);
		
		// 调用对象的clone()方法
		
		// 序列号加载
	}
}
