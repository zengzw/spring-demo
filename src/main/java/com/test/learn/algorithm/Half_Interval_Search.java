package com.test.learn.algorithm;

public class Half_Interval_Search {

	public static void main(String[] args) {

		int[] arrays = {1,2,4,6,8,9,10,12};
		int value = 4;
		
		System.out.println(getIndex(arrays, value,0,0));
		
		//System.out.println(getIndex2(arrays, value));
		
	}
	
	/**
	 * 递归
	 * 
	 * @param array
	 * @param value
	 * @param maxLength
	 * @param minLength
	 * @return
	 */
	public static int getIndex(int array[],int value,int maxLength,int minLength){
		//定义 最大 和  最小
		int max = maxLength == 0 ?array.length -1 : maxLength;
		int min = minLength;
		
		//取中间值
		int mid = (max + min) / 2;
		
		System.out.println("----mid:"+mid + " value:"+array[mid]);
		if(array[mid] > value){
			max = mid - 1;
			return getIndex(array, value,max,min);
			
		}else if (array[mid] < value){
			min = min + 1;
			return getIndex(array, value,max,min);
		}else if(array[mid] == value) {
			return mid;
		}

		return -1;
		
	}
    
	
	/**
	 * 二分查找
	 * @param 数组集合
	 * @param 被查找的值
	 * @return 返回该值在数组中的索引(如果没有返回 -1)
	 */
	public static int getIndex2(int array[], int value) {

	    //定义最大索引和最小索引
	    int max = array.length - 1;
	    int min = 0;
	    //计算中间索引
	    int mid = (max + min) / 2;

	    //拿中间的索引的值和好查找的值进行比较
	    while (array[mid] != value) {
	        if (array[mid] > value) {
	            max = mid - 1;
	        } else if (array[mid] < value) {
	            min = min + 1;
	        }
	        //数组中没有该元素
	        if (min > max) {
	            return -1;
	        }
	        mid = (max + min) / 2;
	    }
	    return mid;
	}
}
