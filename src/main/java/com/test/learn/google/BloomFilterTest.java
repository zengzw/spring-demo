package com.test.learn.google;

import java.util.HashSet;
import java.util.UUID;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {


	private static BloomFilter<Integer> bf = null;
	private static HashSet<Integer> set = null;
	private static final Integer NUMBER = 1000000;

	public static void main(String[] args) {
		//采用工厂方法实例化BloomFilter对象
		//第一个参数:布隆过滤器的泛型，实际场景中一般用来存放用户唯一编码，即userCode
		//第二个参数:布隆过滤器的容量，一般容量设置为实际容量的1.5倍，因为布隆过滤器的算法
		// 复杂，存放容易，删除一个元素很复杂，所以预设容量最好是实际容量的1.2-1.5倍
		//第三个参数:布隆过滤器的误伤率，默认是0.03。布隆过滤器由于使用哈希函数(hsah function)的
		// 过滤机制，算法复杂，其存在一定的hash碰撞率,存在一定的误伤率，这个误伤率可以
		// 设置，误伤率越小，采用的哈希函数越多，所需容量自然就越大，越准确，根据实际场景
		//设置。

		bf = BloomFilter.create(Funnels.integerFunnel(), NUMBER,0.01);
		for (int i =0; i < NUMBER; i++){
			bf.put(i);
		}

		Integer flag = -1;
		int notExit = 0;
		int exit = 0;
		for (int i = 1; i < 9999; i++){
			if(i%100 == 0){
				flag = i/100;
			}else {
				flag = UUID.randomUUID().hashCode();
			}
			
			System.out.println(flag);
			//如果布隆过滤器中不存在这个用户直接返回，将流量挡掉  
			if (!bf.mightContain(flag)){
				notExit++;
			}else{
				exit++;
			}

		}
		
		System.out.println("exits:"+exit +" not exits:"+notExit);

	}

}
