/*
 * @Project Name: springmvcdemo
 * @File Name: ValidateEntity.java
 * @Package Name: com.test.learn.validate.entity
 * @Date: 2017年8月17日上午9:13:35
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.validate.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年8月17日上午9:13:35
 * @see
 */
public class ValidateEntity {
	
	@NotNull(message="用户名，不允许为空")
	private String userName;
	
	
	private Integer age;
	
	
	/**
	 * 方法j校验
	 * 
	 * TODO
	 * @date 2017年8月17日上午9:16:57
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param validate
	 */
	public void validate(@NotBlank(message="validae is not null")String validate){
		System.err.println("validate-----success"+validate);
	}


	
	public String getUserName() {
		return userName;
	}


	
	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	public Integer getAge() {
		return age;
	}


	
	public void setAge(Integer age) {
		this.age = age;
	}
}
