package com.test.learn.jdk.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{

		//通过反射获取Unsafe实例
		Field f =   Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe)f.get(null);

		System.out.println(unsafe);
		
		
		/*
		 * 分配堆外内存
		 */
		
		long allocateAddress = unsafe.allocateMemory(1L);
		unsafe.putByte(allocateAddress, (byte)100);
		byte shortValue = unsafe.getByte(allocateAddress);
		System.out.println(shortValue);
		
		/*
		 * 重新分配
		 */
		
		unsafe.reallocateMemory(allocateAddress, 8L);
		unsafe.putLong(allocateAddress, 1024);
		System.out.println(unsafe.getLong(allocateAddress));
		
		/**
		 * 回收内存
		 */
		unsafe.freeMemory(allocateAddress);
		System.out.println("回收内存后："+unsafe.getLong(allocateAddress));
		
		
		

	}
}
