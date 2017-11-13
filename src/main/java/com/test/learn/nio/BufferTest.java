/*
 * @Project Name: springmvcdemo
 * @File Name: BufferTest.java
 * @Package Name: com.test.learn.nio
 * @Date: 2017年11月3日上午10:44:32
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.nio;

import java.nio.IntBuffer;

/**
 *  将数据写入到 Buffer 中.
	调用 Buffer.flip()方法, 将 NIO Buffer 转换为读模式.
	从 Buffer 中读取数据
	调用 Buffer.clear() 或 Buffer.compact()方法, 将 Buffer 转换为写模式.

   Capacity

	一个内存块会有一个固定的大小, 即容量(capacity), 我们最多写入capacity 个单位的数据到 Buffer 中, 例如一个 DoubleBuffer, 其 Capacity 是100, 那么我们最多可以写入100个 double 数据.

	Position

	当从一个 Buffer 中写入数据时, 我们是从 Buffer 的一个确定的位置(position)开始写入的. 在最初的状态时, position 的值是0. 每当我们写入了一个单位的数据后, position 就会递增一.
	当我们从 Buffer 中读取数据时, 我们也是从某个特定的位置开始读取的. 当我们调用了 filp()方法将 Buffer 从写模式转换到读模式时, position 的值会自动被设置为0, 每当我们读取一个单位的数据, position 的值递增1.
	position 表示了读写操作的位置指针.

	limit

	limit - position 表示此时还可以写入/读取多少单位的数据.
	例如在写模式, 如果此时 limit 是10, position 是2, 则表示已经写入了2个单位的数据, 还可以写入 10 - 2 = 8 个单位的数据.
 * 
 * 
 * 
 * 
 * 
 * @author zengzw-1220
 * @date 2017年11月3日上午10:44:32
 * @see
 */
public class BufferTest {

	public static void main(String[] args) {
		
		test();
		
//		test1();
		
		System.out.println("--------------");
		
//		test2();
		
//		test3();
	}
	
	
	public static void test(){
	IntBuffer buffer = IntBuffer.allocate(3);
		buffer.put(1122323);
		buffer.put(1);

		System.out.println(buffer.position() +" limit:"+buffer.position());
		
		buffer.flip();

		System.out.println(buffer.position() +" :limit:"+buffer.limit());
		
		System.out.println("capacity:"+buffer.capacity());
		System.out.println("limit:"+buffer.limit());
		System.out.println("limit:"+buffer.limit());
		System.out.println(buffer.get(1));
		System.out.println(buffer.get());

	}

	/**
	 * Buffer.rewind()方法可以重置 position 的值为0, 因此我们可以重新读取/写入 Buffer 了.
	 * 
	 * @date 2017年11月3日上午10:59:48
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	public static void test1(){
		IntBuffer intBuffer = IntBuffer.allocate(2);
		intBuffer.put(1);
		intBuffer.put(2);
		System.err.println("after put position: " + intBuffer.position());

		intBuffer.rewind();
		System.err.println("after rewind position: " + intBuffer.position());
		intBuffer.put(1);
		intBuffer.put(2);
		System.err.println("after put position: " + intBuffer.position());


		intBuffer.flip();
		System.err.println("after flip position: " + intBuffer.position());
		intBuffer.get();
		intBuffer.get();
		System.err.println("after get position: " + intBuffer.position());

		intBuffer.rewind();
		System.err.println(" after rewind position: " + intBuffer.position());
		System.err.println(intBuffer.get());
		System.err.println(intBuffer.get());
		intBuffer.rewind();
		intBuffer.put(1, 3);
		System.err.println(intBuffer.get());
		System.err.println(intBuffer.get());

	}



	/**
	 * 我们可以通过调用 Buffer.mark()将当前的 position 的值保存起来, 随后可以通过调用 Buffer.reset()方法将 position 的值回复回来.
	 * 
	 * @date 2017年11月3日上午11:00:10
	 * @author zengzw-1220
	 * @since 1.0.0
	 */
	public static void test2(){
		IntBuffer intBuffer = IntBuffer.allocate(2);
		intBuffer.put(1);
		intBuffer.put(2);
		intBuffer.flip();
		System.err.println(intBuffer.get());
		System.err.println("position: " + intBuffer.position());
		
		intBuffer.mark();
		System.err.println(intBuffer.get());

		System.err.println("position: " + intBuffer.position());
		intBuffer.reset();
		System.err.println("position: " + intBuffer.position());
		System.err.println(intBuffer.get());
	}
	
	
	
	public static void test3(){
		IntBuffer intBuffer = IntBuffer.allocate(2);
		intBuffer.flip();
		System.err.println("position: " + intBuffer.position());
		System.err.println("limit: " + intBuffer.limit());
		System.err.println("capacity: " + intBuffer.capacity());

		// 这里不能读, 因为 limit == position == 0, 没有数据.
		//System.err.println(intBuffer.get());

		intBuffer.clear();
		System.err.println("position: " + intBuffer.position());
		System.err.println("limit: " + intBuffer.limit());
		System.err.println("capacity: " + intBuffer.capacity());

		// 这里可以读取数据了, 因为 clear 后, limit == capacity == 2, position == 0,
		// 即使我们没有写入任何的数据到 buffer 中.
		System.err.println(intBuffer.get()); // 读取到0
		System.err.println(intBuffer.get()); // 读取到0
	}
}
