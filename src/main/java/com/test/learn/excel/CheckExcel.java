/*
 * @Project Name: springmvcdemo
 * @File Name: CheckExcel.java
 * @Package Name: com.test.learn.excel
 * @Date: 2017年11月30日上午10:44:27
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.excel;

import java.util.Map;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月30日上午10:44:27
 * @see
 */
public interface  CheckExcel {
	
	/**
     * 返回true合法
     *
     * @param data      excel中每一行的数据
     * @return
     */
    public boolean check(Map<String, String> data);
}
