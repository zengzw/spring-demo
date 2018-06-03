package com.test.learn.jdk;

import java.util.ArrayList;
import java.util.List;

import com.test.learn.mthread.proctom.Test2;

/**
 * 泛型
 * 
 * https://segmentfault.com/a/1190000008423240
 * 
 * @author home
 *
 */
public class GenericityTest {
	
	private int age;
	

/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		return result;
	}
*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericityTest other = (GenericityTest) obj;
		if (age != other.age)
			return false;
		return true;
	}
	
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static void main(String[] args) {
		
//		testWrite(new ArrayList<Integer>());
		
		GenericityTest test = new GenericityTest();
		test.setAge(11);
		
		GenericityTest test1 = new GenericityTest();
		test1.setAge(11);
		
		System.out.println(test.equals(test1));
		System.out.println(test.hashCode() +" : "+ test1.hashCode());
		
	}
	
	public static void  testWrite(List<? super Integer> list){
		list.add(220);
		
		List<Integer> numberArray = new ArrayList<Integer>(); 
		numberArray.add(22);
		
		testRead(numberArray);
	}
	
	public static void testRead(List<? extends Integer> numberArray){
		for(Integer result : numberArray){
			System.out.println(result);
		}
	}
}
