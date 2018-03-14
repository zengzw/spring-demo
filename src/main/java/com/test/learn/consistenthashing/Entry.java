/*
 * @Project Name: springmvcdemo
 * @File Name: Entry.java
 * @Package Name: com.test.learn.consistenthashing
 * @Date: 2018年3月14日上午10:41:13
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.consistenthashing;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月14日上午10:41:13
 * @see
 */
public class Entry {
	
	public String getKey() {
	
		return key;
	}

	
	public void setKey(String key) {
	
		this.key = key;
	}

	private String key;

    Entry(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
