/*
 * @Project Name: springmvcdemo
 * @File Name: LongEvent.java
 * @Package Name: com.test.learn.disruptor
 * @Date: 2018年3月27日下午3:23:57
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disruptor;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月27日下午3:23:57
 * @see
 */
public class LongEvent {
	  private long value;
      
      public LongEvent() {
          this.value = 0L;
      }
       
      public void set(long value) {
          this.value = value;
      }
       
      public long get() {
          return this.value;
      }
}
