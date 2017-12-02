/*
 * @Project Name: springmvcdemo
 * @File Name: ReflectionUtil.java
 * @Package Name: com.test.learn.excel
 * @Date: 2017年11月30日下午6:28:44
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;


/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月30日下午6:28:44
 * @see
 */
public class ExcelReflectionUtil {
	
	public static List<Excel> getListExcelAnoationByClass(Class<?> clz){
		Field[] fields = clz.getDeclaredFields();
		List<Excel> list = new ArrayList<>();
		for(Field field : fields){
			Excel excel = field.getAnnotation(Excel.class);
			excel.variable();
			
			if(excel != null){
				list.add(excel);
			}
		}
		
		return list;
	}
	
	
	public static Map<Object, Object> getMapExcelAnoationByClass(Class<?> clz){
		
		Field[] fields = clz.getDeclaredFields();
		Map<Object, Object> result = new HashMap<>();
		for(Field field : fields){
			Excel excel = field.getAnnotation(Excel.class);
			if(excel != null && !excel.skip()){
				result.put( excel.name(),field.getName());
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {

		List<Excel> list = getListExcelAnoationByClass(ExcelModel.class);
		for(Excel e:list){
			System.out.println(e.name() + " : " + e.skip());
		} 
	}

}
