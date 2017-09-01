/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.context.ApplicationContext;

import com.test.springmvc.model.User;

import net.sf.cglib.reflect.FastClass;

/**
 *
 * @author zengzw
 * @date 2017年7月5日
 */
public class CollectionsTest {


    public static void main(String[] args) {

        String s = "abc";
        StringBuffer buffer = new StringBuffer(s);
        System.err.println(buffer.reverse().toString());

        //        System.out.println(1 >> 1);
        Object [] original = {1,2,3,4,5,6};
        Object[] news = new Object[6];
        //        Object[] news = Arrays.copyOf(original,20);

        //        System.arraycopy(original, 0, news, 0,6);

        List list = new ArrayList();
        list.add(20);
        list.add("44");

        list.toArray(news);
        System.out.println(Arrays.toString(news));

        System.out.println(10 /.75f);
        System.out.println(Math.max((int) (10 /.75f) + 5, 16));
        
        Map<String, Object> maps = new HashMap<>();
        maps.put("kk","bb");
        maps.put("kk","cc");
        
        Set<Object> sets = new HashSet();
        sets.add("kktalk");
        sets.add("kktalk1");
       
        TreeMap treeMap;
        System.out.println(sets);
        //       ConcurrentHashMap<K, V>
        //        
        /*   BufferedReader reader;
        FileInputStream fileInputStream;
        ObjectInputStream  objectInputStream;
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        List<String> lst = new LinkedList();
        lst.add("aa");
        lst.add("dd");
        lst.add("bb");

        System.out.println(lst);
        TreeSet nums = new TreeSet();
        //向TreeSet中添加四个Integer对象
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);

        //输出集合元素，看到集合元素已经处于排序状态
        System.out.println(nums);

        //输出集合里的第一个元素
        System.out.println(nums.first());

        //输出集合里的最后一个元素
        System.out.println(nums.last());

        //返回小于4的子集，不包含4
        System.out.println(nums.headSet(4));

        //返回大于5的子集，如果Set中包含5，子集中还包含5
        System.out.println(nums.tailSet(5));

        //返回大于等于-3，小于4的子集。
        System.out.println(nums.subSet(-3 , 4));
        List books = new ArrayList();
        byte[] b1,b2,b3,b4;//定义变量
        b1=new byte[1024*1024];//分配 1MB 堆空间，考察堆空间的使用情况
        b2=new byte[1024*1024];
        b3=new byte[1024*1024];
        b4=new byte[1024*1024];*/
        
/*        ApplicationContext context = null;
        context.getBeansWithAnnotation((Class<? extends Annotation>) CollectionsTest.class);*/
        	FastClass fastClass = FastClass.create(User.class);
        		
    }
}
