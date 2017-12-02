/*
 * @Project Name: springmvcdemo
 * @File Name: ExcelModel.java
 * @Package Name: com.test.learn.excel
 * @Date: 2017年11月30日下午6:14:23
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.excel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月30日下午6:14:23
 * @see
 */

public class ExcelModel {

	@Excel(name="订单编号")
	private int id;

	@Excel(name="供应商订单编号")
	private String supplierCode;

	@Excel(name="服务供应商",skip=true)
	private String supplierName;
	
	private String desc;


	
	public String getDesc() {
	
		return desc;
	}


	
	public void setDesc(String desc) {
	
		this.desc = desc;
	}


	public int getId() {

		return id;
	}


	public void setId(int id) {

		this.id = id;
	}


	
	public String getSupplierCode() {
	
		return supplierCode;
	}



	
	public void setSupplierCode(String supplierCode) {
	
		this.supplierCode = supplierCode;
	}



	
	public String getSupplierName() {
	
		return supplierName;
	}



	
	public void setSupplierName(String supplierName) {
	
		this.supplierName = supplierName;
	}



	public static void main(String[] args) {
		
		Field[] fields = ExcelModel.class.getDeclaredFields();
		for(Field field : fields){
			System.out.println("field name:"+field.getName());
			Excel excel = field.getAnnotation(Excel.class);
			if(excel != null)
				System.out.println("anotation:"+excel.name());
		}
	}
}
