/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.io.Serializable;
import java.util.Arrays;

import clojure.lang.Obj;

/**
 *
 * @author zengzw
 * @date 2017年8月9日
 */
public class  LoopQueue<T> implements Serializable{  
    /** 
     * @Fields serialVersionUID : TODO 
     */  
    private static final long serialVersionUID = -3670496550272478781L;  
    
    private int DEFAULT_SIZE = 10;  
    
    private int capacity;//保存数组的长度  
    
    private Object[] elementData;//定义一个数组用于保存循环队列的元素  
    
    private int front = 0;//队头  
    
    private int rear = 0;//队尾  
    
    //以默认数组长度创建空循环队列  
    public LoopQueue() {  
      capacity = DEFAULT_SIZE;  
      elementData = new Object[capacity];  
    }  
    
    //以一个初始化元素来创建循环队列  
    public LoopQueue(T element) {  
      this();  
      elementData[0] = element;  
      rear++;  
    }  
    
    /** 
     * 以指定长度的数组来创建循环队列 
     * @param element 指定循环队列中第一个元素 
     * @param initSize 指定循环队列底层数组的长度 
     */  
    public LoopQueue(T element, int initSize) {  
      this.capacity = initSize;  
      elementData = new Object[capacity];  
      elementData[0] = element;  
      rear++;  
    }  
    
    //获取循环队列的大小  
    public int size() {  
      if (isEmpty()) {  
        return 0;  
      }  
      return rear > front ? rear - front : capacity - (front - rear);  
    }  
    
    //插入队列  
    public void add(T element) {  
      if (rear == front && elementData[front] != null) {  
        throw new IndexOutOfBoundsException("队列已满的异常");  
      }  
      elementData[rear++] = element;  
      //如果rear已经到头，那就转头  
      rear = rear == capacity ? 0 : rear;  
    }  
    
    //移除队列  
    public T remove() {  
      if (isEmpty()) {  
        throw new IndexOutOfBoundsException("空队列异常");  
      }  
      //保留队列的rear端的元素的值  
      T oldValue = (T) elementData[front];  
      //释放队列的rear端的元素  
      elementData[front++] = null;  
      //如果front已经到头，那就转头  
      front = front == capacity ? 0 : front;  
      return oldValue;  
    }  
    
    //返回队列顶元素，但不删除队列顶元素  
    public T element() {  
      if (isEmpty()) {  
        throw new IndexOutOfBoundsException("空队列异常");  
      }  
      return (T) elementData[front];  
    }  
    
    //判断循环队列是否为空队列  
    public boolean isEmpty() {  
      //rear==front且rear处的元素为null  
      return rear == front && elementData[rear] == null;  
    }  
    
    //清空循环队列  
    public void clear() {  
      //将底层数组所有元素赋为null  
      Arrays.fill(elementData, null);  
      front = 0;  
      rear = 0;  
    }  
    
    public String toString() {  
      if (isEmpty()) {  
        return "[]";  
      } else {  
        //如果front < rear，有效元素就是front到rear之间的元素  
        if (front < rear) {  
          StringBuilder sb = new StringBuilder("[");  
          for (int i = front; i < rear; i++) {  
            sb.append(elementData[i].toString() + ", ");  
          }  
          int len = sb.length();  
          return sb.delete(len - 2, len).append("]").toString();  
        }  
        //如果front >= rear，有效元素为front->capacity之间、0->front之间的  
        else {  
          StringBuilder sb = new StringBuilder("[");  
          for (int i = front; i < capacity; i++) {  
            sb.append(elementData[i].toString() + ", ");  
          }  
          for (int i = 0; i < rear; i++) {  
            sb.append(elementData[i].toString() + ", ");  
          }  
          int len = sb.length();  
          return sb.delete(len - 2, len).append("]").toString();  
        }  
      }  
    }  
    
    public static void main(String[] args) {  
      LoopQueue<String> queue = new LoopQueue<String>("aaaa", 3);  
      //添加两个元素  
      queue.add("bbbb");  
      queue.add("cccc");  
      //此时队列已满  
      System.out.println(queue);  
      //删除一个元素后，队列可以再多加一个元素  
      queue.remove();  
      System.out.println("删除一个元素后的队列：" + queue);  
    /*  //再次添加一个元素，此时队列又满  
      queue.add("dddd");  
      System.out.println(queue);  
      System.out.println("队列满时的长度：" + queue.size());  
      //删除一个元素后，队列可以再多加一个元素  
      queue.remove();  
      //再次加入一个元素，此时队列又满  
      queue.add("eeee");  
      System.out.println(queue);  */
      
      
      Object[] ints = new Object[5];
      System.out.println(Arrays.toString(ints));
      
      ints = Arrays.copyOf(ints, 10);
      
      System.out.println(Arrays.toString(ints));
      
  
    }  
  }  
