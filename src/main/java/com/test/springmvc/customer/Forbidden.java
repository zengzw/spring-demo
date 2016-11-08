/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.customer;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.NotEmpty;

import static java.lang.annotation.ElementType.*;  
import static java.lang.annotation.RetentionPolicy.*; 
/**
 * 自定义验证器
 * @author zengzw
 * @date 2014年10月13日
 */
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=ForbiddenValidator.class)//指定验证器
@Documented
public @interface Forbidden {

    //默认验证消息
    String message() default "{forbidden.word}";
    
    //分组
    Class<?>[] groups() default {};
    
    //负载
    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @NotEmpty} annotations on the same element.
     * 指定多个时使用
     */
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Forbidden[] value();
    }
}
