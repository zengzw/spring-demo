package com.test.springmvc.utils;

public enum ContentTypeEnum {
	
	Formurlencoded("application/x-www-form-urlencoded"),
	
	Json("application/json");

	private final String value;
	private ContentTypeEnum(String value){
		this.value = value;
	}
	public String value(){
		return this.value;
	}
	

}
