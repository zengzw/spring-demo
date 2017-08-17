/*
 * @Project Name: springmvcdemo
 * @File Name: ValidateUtils.java
 * @Package Name: com.test.learn.validate
 * @Date: 2017年8月17日上午9:13:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.validate;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

import com.test.learn.validate.entity.ValidateEntity;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年8月17日上午9:13:49
 * @see
 */
public class ValidateUtils {


	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		//校验实体
		ValidateEntity entity = new ValidateEntity();
		Set<ConstraintViolation<ValidateEntity>> validateResult = validator.validate(entity);

		for(ConstraintViolation<ValidateEntity> constraintViolation :validateResult){
			System.out.println(constraintViolation.getMessage()
					+"\n" + constraintViolation.getInvalidValue()+"\n"
					+ constraintViolation.getPropertyPath());
		}


		System.out.println("================校验方法======================");
		//校验某个方法的参数
		ExecutableValidator executableValidator = validator.forExecutables();
		Method method = entity.getClass().getMethod("validate", String.class);
		Object[] paramsValue = {null};
		Set<ConstraintViolation<ValidateEntity>> methodValidators  = executableValidator.validateParameters(entity, method,paramsValue);
		for(ConstraintViolation<ValidateEntity> constraintViolation : methodValidators){
			System.out.println(constraintViolation.getMessage()
					+"\n" + constraintViolation.getInvalidValue()+"\n"
					+ constraintViolation.getPropertyPath());
		}
	}


}
