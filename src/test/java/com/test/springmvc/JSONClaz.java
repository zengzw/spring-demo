/*
 * @Project Name: springmvcdemo
 * @File Name: JSONClaz.java
 * @Package Name: com.test.springmvc
 * @Date: 2018年1月23日下午12:16:46
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月23日下午12:16:46
 * @see
 */
public class JSONClaz {
	
	String name;
	
	String textValue;
	
	String textDesc;
	
	/**
	 * 字段类型。text,textarea,checkbox,radio,select,number
	 */
	String fileType;

	/**
	 * 用户输入值
	 */
	String inputValue;
	
	
	public String getInputValue() {
	
		return inputValue;
	}




	
	public void setInputValue(String inputValue) {
	
		this.inputValue = inputValue;
	}




	public String getFileType() {
	
		return fileType;
	}



	
	public void setFileType(String fileType) {
	
		this.fileType = fileType;
	}


	private List<JSONClaz> subList;
	
	
	public List<JSONClaz> getSubList() {
	
		return subList;
	}


	
	public void setSubList(List<JSONClaz> subList) {
	
		this.subList = subList;
	}


	public String getName() {
	
		return name;
	}

	
	public void setName(String name) {
	
		this.name = name;
	}

	
	public String getTextValue() {
	
		return textValue;
	}

	
	public void setTextValue(String textValue) {
	
		this.textValue = textValue;
	}

	
	public String getTextDesc() {
	
		return textDesc;
	}

	
	public void setTextDesc(String textDesc) {
	
		this.textDesc = textDesc;
	} 
	
	
	public static void main(String[] args) {
		List<JSONClaz> parentList = new ArrayList<>();
		JSONClaz j = new JSONClaz();
		j.setName("biaoti");
		j.setTextDesc("教学计划");
		
		List<JSONClaz> subList = new ArrayList<>();
		JSONClaz subL = new JSONClaz();
		subL.setName("anli");
		subL.setTextDesc("学情分析");
		subL.setTextValue("这是值..........");
		
		JSONClaz subL1 = new JSONClaz();
		subL1.setName("xueximub");
		subL1.setTextDesc("学情目标");
		subL1.setTextValue("这是值..........");
		
		List<JSONClaz> subL1ObjList = new ArrayList<>();
		JSONClaz subL1Obj = new JSONClaz();
		subL1Obj.setName("xueximubSub");
		subL1Obj.setTextDesc("xueximubSub");
		subL1Obj.setTextValue("这是值..........");
		subL1ObjList.add(subL1Obj);
		subL1.setSubList(subL1ObjList);
		
		subList.add(subL);
		subList.add(subL1);
		j.setSubList(subList);
		
		parentList.add(j);
		
		
		
		JSONClaz k = new JSONClaz();
		k.setName("jiaoxuexinxi");
		k.setTextDesc("教学信息");
		
		List<JSONClaz> subkList = new ArrayList<>();
		JSONClaz subLk1 = new JSONClaz();
		subLk1.setName("anli");
		subLk1.setTextDesc("教学目标");
		subLk1.setTextValue("这是值..........");
		
		JSONClaz subLk2 = new JSONClaz();
		subLk2.setName("xueximub");
		subLk2.setTextDesc("重点领域");
		subLk2.setTextValue("这是值..........");
		
		JSONClaz subLk3 = new JSONClaz();
		subLk3.setName("xueximub");
		subLk3.setTextDesc("场地器材");
		subLk3.setTextValue("这是值..........");
		
		subkList.add(subLk1);
		subkList.add(subLk2);
		subkList.add(subLk3);
		k.setSubList(subkList);
		
		parentList.add(k);
		System.out.println(JSON.toJSONString(parentList));
		
		
		
	}
}
