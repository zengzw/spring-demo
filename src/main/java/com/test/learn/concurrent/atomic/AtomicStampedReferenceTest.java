/*
 * @Project Name: springmvcdemo
 * @File Name: AtomicStampedReferenceTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月10日上午10:51:01
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *  解决ABA的问题
 *  
 *   Pair （reference + stamp)
 *   对象 + 时间戳
 *   
 *   
 * @author zengzw-1220
 * @date 2018年5月10日上午10:51:01
 * @see
 */
public class AtomicStampedReferenceTest {

	/*public static void main(String[] args) {

		AtomicStampedReference asr = new AtomicStampedReference(10,0);
		 int[] mark = new int[1];

		asr.compareAndSet(10, 11, asr.getStamp(),asr.getStamp()+1);
		asr.compareAndSet(11, 12, asr.getStamp(),asr.getStamp()+1);

		System.out.println(asr.get(mark));
	}*/


	static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(
			19, 0);

	public static void main(String[] args)
	{
		for (int i = 0; i < 3; i++)
		{
			final int timestamp = money.getStamp();
			new Thread()
			{
				public void run()
				{

					while(true){
						while (true)
						{
							Integer m = money.getReference();
							if (m < 20)
							{
								System.out.println("----reference:"+m +"  timestamp:"+timestamp);
								boolean isTrue = money.compareAndSet(m, m + 20, timestamp,
										timestamp + 1);
								if (isTrue)
								{
									System.out.println("充值成功，余额:"
											+ money.getReference());
									break;
								}else{
									break;
								}
							}
							else
							{
								break;
							}
						}
						
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
			}.start();
		}

		new Thread()
		{
			public void run()
			{
				for (int i = 0; i < 100; i++)
				{
					while (true)
					{
						int timestamp = money.getStamp();
						Integer m = money.getReference();
						if (m > 10)
						{
							if (money.compareAndSet(m, m - 10, timestamp,
									timestamp + 1))
							{
								System.out.println("消费10元，余额:"
										+ money.getReference());
								break;
							}
						}else {
							break;
						}
					}
					try
					{
						Thread.sleep(100);
					}
					catch (Exception e)
					{
						// TODO: handle exception
					}
				}
			};
		}.start();
	}
}
