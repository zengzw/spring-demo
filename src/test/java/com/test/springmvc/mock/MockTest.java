/*
 * @Project Name: springmvcdemo
 * @File Name: MockTest.java
 * @Package Name: com.test.springmvc.mock
 * @Date: 2018年1月11日下午4:00:04
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.mock;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.data.Index;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
/**
 *  @see http://static.javadoc.io/org.mockito/mockito-core/2.9.0/org/mockito/Mockito.html#verification
 *  
 * @author zengzw-1220
 * @date 2018年1月11日下午4:00:04
 * @see
 */
public class MockTest {


	@Test
	public void testMock1(){
		List mockList = mock(List.class);
		mockList.add("aa");
		mockList.clear();

		verify(mockList).add("aa");
	}


	@Test(expected=RuntimeException.class)
	public void testMock2(){
		//You can mock concrete classes, not just interfaces
		LinkedList<String> linkedList = mock(LinkedList.class);

		//stubbing
		when(linkedList.get(0)).thenReturn("first");
		when(linkedList.get(1)).thenThrow(new RuntimeException());
		
		verify(linkedList).get(0);
		
		
		System.out.println(linkedList.get(0));
		
		assertThat(linkedList.get(0)).isEqualTo("first");
		
		//throw RuntimeException
//		assertThat(linkedList.get(1));
		
		
	}
	
	
	@Test
	public void testMock3(){
		List mock = mock(List.class,RETURNS_SMART_NULLS);
		
		System.out.println(mock.get(0));
		
		System.out.println(mock.toArray().length);
	}
	
	
	/**
	 * 模拟抛异常
	 * 
	 * @date 2018年1月12日下午3:21:24
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	@Test(expected = RuntimeException.class)
	public void testMock4(){
		List list = mock(List.class);
		
		doThrow(new RuntimeException()).when(list.get(anyInt()));
		
		list.get(0);
	}
	
	
	
	@Mock
	private List mockList;
	
	/*@org.junit.Before
	public void before(){
		MockitoAnnotations.initMocks(this);
	}*/
	
	public MockTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void testMock5(){
		mockList.add(1);
		
		verify(mockList).add(1);
	}
}
