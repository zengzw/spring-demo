/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.javassist;

import java.io.IOException;

import org.springframework.jca.cci.core.support.CciDaoSupport;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 *
 * @author zengzw
 * @date 2016年12月29日
 */
public class Point {
    int x, y;
    void move(int dx, int dy) { 
        x += dx; 
        y += dy;
        System.out.println("------");
        
    }
    
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.test.annotation.Point");
        
        CtMethod method = ctClass.getDeclaredMethod("move");
        method.insertBefore("{ System.out.println($1); System.out.println($2); }");
        
       Point point = (Point)ctClass.toClass().newInstance();
       point.move(22, 33);
        
        
    }
}
