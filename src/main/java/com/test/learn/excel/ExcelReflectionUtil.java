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

import org.apache.storm.command.list;

import com.test.springmvc.utils.StringUtils;


/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月30日下午6:28:44
 * @see
 */
public class ExcelReflectionUtil {

	public static List<Excel> getListExcelAnoationByClass(Class<?> clz){
		Field[] fields = getFieldByClass(clz);
		List<Excel> list = new ArrayList<>();
		for(Field field : fields){
			Excel excel = field.getAnnotation(Excel.class);
			if(excel != null){
				list.add(excel);
			}
		}

		return list;
	}


	public static Field[] getFieldByClass(Class<?> clz){
		Field[] fields = clz.getDeclaredFields();
		return fields;
	}


	public static String[] getFieldNameByClass(Class<?> clz){
		Field[] fields = clz.getDeclaredFields();
		String[] fieldName = new String[fields.length];

		for(int i = 0; i<fields.length;i++){
			Field field = fields[i];
			fieldName[i] = field.getName();
		}

		return fieldName;
	}


	public static String[] getTitleByClass(Class<?> clz){
		List<Excel> listExcel = getListExcelAnoationByClass(clz);
		String[] fieldName = new String[listExcel.size()];

		for(int i = 0; i<listExcel.size(); i++){
			Excel excel = listExcel.get(i);
			fieldName[i] = excel.name();
		}
		
		return fieldName;
	}



	public static String getHeaderExcelAnoationByClass(Class<?> clz){
		List<Excel> list = getListExcelAnoationByClass(clz);
		for(Excel excel : list){
			if(StringUtils.isNotEmpty(excel.head())){
				return excel.head();
			}
		}

		return "";
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


	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

		/*List<Excel> list = getListExcelAnoationByClass(ExcelModel.class);
		for(Excel e:list){
			System.out.println(e.name() + " : " + e.skip());
		} */

		Field[] fields = getFieldByClass(ExcelModel.class);
		ExcelModel excelModel = new ExcelModel();
		for(Field field : fields){
			field.setAccessible(true);

			System.out.println(field.getName() +":" + field.get(excelModel));
		}
		System.out.println(fields.length);
	}

}
