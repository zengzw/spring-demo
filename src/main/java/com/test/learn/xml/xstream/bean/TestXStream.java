/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.xml.xstream.bean;

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author zengzw
 * @date 2016年12月20日
 */
public class TestXStream {

    public static void main(String[] args) {
        XStream stream = new XStream();
        Student student = new Student();
        student.setAge(11);
        student.setName("stu");
        student.setGrade("grade");

        Teacher teacher = new Teacher();
        teacher.setNaem("teacher");
        student.setTeacher(teacher);

        stream.alias("xml", Student.class);
        String result = stream.toXML(student);

        System.out.println(result);
    }
}
