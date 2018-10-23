package com.test.learn.jdk.unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;


public class UnSafeTest2 {

	// setup to use Unsafe.compareAndSwapInt for updates
	public static Unsafe unsafe = null;
	public static final long valueOffset;

	static {
		Field f = null;
		try {
			f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		try {
			unsafe = (Unsafe)f.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			valueOffset = unsafe.objectFieldOffset
					(AtomicInteger.class.getDeclaredField("value"));
		} catch (Exception ex) { throw new Error(ex); }
	}

	private volatile int value;

	public UnSafeTest2(int initialValue) {
		this.value = initialValue;
	}

	public static void main(String[] args) {
		UnSafeTest2 unSafeTest = new UnSafeTest2(20);
		
		//根据地址获取对象的值
		int value = unsafe.getInt(unSafeTest, valueOffset);
		
		System.out.println("---value:"+value);
		System.out.println(unSafeTest.valueOffset);
	}

}
