/*
 * @Project Name: springmvcdemo
 * @File Name: StudentCompare.java
 * @Package Name: com.test.learn.collections
 * @Date: 2018年3月21日上午11:28:30
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.collections;

import java.util.Comparator;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月21日上午11:28:30
 * @see
 */
public class StudentCompare implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {

		int cr = 0;
		int a = o1.getStatus() + o2.getStatus();
		System.out.println("--->"+a);
		
		if(o1.getStatus() > o2.getStatus() ){
			return 1;
		}else if(o1.getStatus() < o2.getStatus()){
			return -1;
		}
		return 0;
	}

}
