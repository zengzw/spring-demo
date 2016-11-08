/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.global;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.alibaba.fastjson.JSON;
import com.test.base.exception.BusinessRuntimeException;

/**
 *
 * @author zengzw
 * @date 2015年6月27日
 */
@Order(1)
@ControllerAdvice
public class GlobalExcetionHandler {

    @ExceptionHandler(BusinessRuntimeException.class)
    public Object businessRuntimeExceptionHandeler(BusinessRuntimeException ex,HttpServletResponse response){

        /*    System.out.println("--------------------------------"+ex.getErrMsg()+" : "+ex.getErrCode());
        ModelAndView model = new ModelAndView("errors/500");
        model.addObject("errMsg", "this is Exception.class");*/
        System.out.println("--------------------------------"+ex.getErrMsg()+" : "+ex.getErrCode());
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("kktalk", "kktalk1");
        maps.put("bb", "bb");

        String result = JSON.toJSONString(maps);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(null != writer){
                writer.close();
            }
        }

        return null;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("error/500");
        model.addObject("errMsg", "this is Exception.class");

        return model;

    }


}
