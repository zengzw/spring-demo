/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.annotation;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 动态修改注解信息
 * 
 * @author zengzw
 * @date 2016年12月27日
 */
public class DynamicAnnotation {

    @JsonIgnore(value=true)
    protected String name;

    protected String test;

    public String getTest() {
        return test;
    }



    public String getName() {
        return name;
    }



    public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException, InstantiationException, IllegalAccessException {
        // get();
        //       set();
        //        addAnnotation();
        addJSONAnnotation();

    }

    public static void get() throws NotFoundException{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.test.annotation.DynamicAnnotation");

        MethodInfo methodInfo = null;
        CtMethod[] methods = ctClass.getDeclaredMethods();
        for(CtMethod method:methods){
            System.out.println("方法名称："+method.getName());
            methodInfo = method.getMethodInfo();  
        }


        CtField[] ctFields = ctClass.getFields();
        for(CtField ctField : ctFields){ //field 不能private
            System.out.println("字段："+ctField.getName());
            FieldInfo fieldInfo = ctField.getFieldInfo();

            ConstPool cp = fieldInfo.getConstPool();  

            //获取注解
            AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
            System.out.println("=-=-=-"+attribute);
            if(attribute == null){
                return;
            }
            Annotation[] annotations = attribute.getAnnotations();
            for(Annotation annotation:annotations){
                System.out.println("\t注解类型："+annotation.getTypeName());
                Set set = annotation.getMemberNames();
                Iterator iterator =set.iterator();
                while(iterator.hasNext()){
                    System.out.println("注解值:"+annotation.getMemberValue(iterator.next().toString()));
                }
            }            
        }
    }


    /**
     * 修改注解
     * @throws NotFoundException
     */
    public static void set() throws NotFoundException{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.test.annotation.DynamicAnnotation");


        //获取类里的所有方法  
        CtMethod[] cms = ctClass.getDeclaredMethods();  
        CtMethod cm = cms[0];      
        System.out.println("方法名称====" + cm.getName());  

        MethodInfo minInfo = cm.getMethodInfo();  
        //获取类里的em属性  
        CtField cf = ctClass.getField("name");  
        FieldInfo fieldInfo = cf.getFieldInfo();    

        System.out.println("属性名称===" + cf.getName());  

        ConstPool cp = fieldInfo.getConstPool();  
        //获取注解信息  
        AnnotationsAttribute attribute2 = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);  
        Annotation annotation = new Annotation("com.fasterxml.jackson.annotation.JsonIgnore", cp);  

        //修改名称为unitName的注解  
        annotation.addMemberValue("value", new BooleanMemberValue(false, cp));  
        attribute2.setAnnotation(annotation);  
        minInfo.addAttribute(attribute2);  

        //打印修改后方法  
        Annotation annotation2 = attribute2.getAnnotation("com.fasterxml.jackson.annotation.JsonIgnore");  
        System.out.println("修改后的值："+annotation2.getMemberValue("value"));
    }


    /**
     * 动态添加  添加注解
     * 
     * @throws NotFoundException 
     * @throws CannotCompileException 
     * @throws IOException 
     */
    public static void addAnnotation() throws NotFoundException, IOException, CannotCompileException{
        ClassPool pool =  ClassPool.getDefault();
        CtClass ctClass = pool.get("com.test.annotation.DynamicAnnotation");

        ClassFile classFile = ctClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();

        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("com.test.annotation.MyAnnotation", constPool); //创建注解类
        annotation.addMemberValue("name", new StringMemberValue("namev", constPool)); //添加属性值
        annotationsAttribute.addAnnotation(annotation);

        //字段，添加注解
        CtField field = ctClass.getFields()[1];
        System.out.println("--"+field.getName());
        FieldInfo fif = field.getFieldInfo();
        fif.addAttribute(annotationsAttribute);

        //方法添加注解
        CtMethod method = ctClass.getDeclaredMethods()[0];
        System.out.println(">>>"+method.getName());
        method.getMethodInfo().addAttribute(annotationsAttribute);

        CtField[] ctFields = ctClass.getFields();
        for(CtField ctField : ctFields){ //field 不能private
            System.out.println("字段："+ctField.getName());
            FieldInfo fieldInfo = ctField.getFieldInfo();


            //获取注解
            AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
            if(attribute == null){
                return;
            }
            Annotation[] annotations = attribute.getAnnotations();
            for(Annotation anno:annotations){
                System.out.println("\t注解类型："+anno.getTypeName());
                Set set = anno.getMemberNames();
                Iterator iterator =set.iterator();
                while(iterator.hasNext()){
                    System.out.println("注解值:"+anno.getMemberValue(iterator.next().toString()));
                }
            }            
        }

    }
    /**
     * 动态添加  添加注解
     * 
     * @throws NotFoundException 
     * @throws CannotCompileException 
     * @throws IOException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public static void addJSONAnnotation() throws NotFoundException, IOException, CannotCompileException, InstantiationException, IllegalAccessException{
        ClassPool pool =  ClassPool.getDefault();
        CtClass ctClass = pool.get("com.test.annotation.JSONTest");

        Class<JSONTest> clz = ctClass.toClass();
        System.out.println(clz.getName());
        Object object = clz.newInstance();
        System.out.println(JSON.toJSONString(object));
        ctClass.defrost();

        ClassFile classFile = ctClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("com.alibaba.fastjson.annotation.JSONField", constPool); //创建注解类
        annotation.addMemberValue("serialize", new BooleanMemberValue(false, constPool)); //添加属性值
        annotationsAttribute.addAnnotation(annotation);

        //字段，添加注解
        CtField field = ctClass.getFields()[0];
        System.out.println("--"+field.getName());
        FieldInfo fif = field.getFieldInfo();
        fif.addAttribute(annotationsAttribute);

        //方法添加注解
        CtMethod method = ctClass.getDeclaredMethods()[0];
        System.out.println(">>>"+method.getName());
        method.getMethodInfo().addAttribute(annotationsAttribute);




      
        System.out.println(JSON.toJSONString(clz.newInstance()));


    /*    CtField[] ctFields = ctClass.getFields();
        for(CtField ctField : ctFields){ //field 不能private
            System.out.println("字段："+ctField.getName());
            FieldInfo fieldInfo = ctField.getFieldInfo();


            //获取注解
            AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
            if(attribute == null){
                return;
            }
            Annotation[] annotations = attribute.getAnnotations();
            for(Annotation anno:annotations){
                System.out.println("\t注解类型："+anno.getTypeName());
                Set set = anno.getMemberNames();
                Iterator iterator =set.iterator();
                while(iterator.hasNext()){
                    System.out.println("注解值:"+anno.getMemberValue(iterator.next().toString()));
                }
            }            
        }*/

    }

}
