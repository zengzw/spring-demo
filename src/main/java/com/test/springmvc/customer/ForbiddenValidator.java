/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

/**
 *自定义注解类
 * @author zengzw
 * @date 2014年10月13日
 */
public class ForbiddenValidator implements ConstraintValidator<Forbidden, String>{
    private String[] forbiddenWords = {"admin"};  
    @Override
    public void initialize(Forbidden constraintAnnotation) {
    //初始化，得到注解的数据
        System.out.println(constraintAnnotation.message()+"_"+constraintAnnotation.groups());
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if(StringUtils.isEmpty(value)){
          return true;
      }
      
      for(String word:forbiddenWords){
          //验证失败
          if(value.contains(word)){
              return false;
          }
      }
        return true;
    }

}
