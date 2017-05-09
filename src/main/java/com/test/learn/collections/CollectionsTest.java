/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;


/**
 *
 * @author zengzw
 * @date 2017年2月9日
 */
public class CollectionsTest {

    
    public static void main(String[] args) {
      /*  testHashSet();
        
        testLickHashSet();
        
        
        testTreeSet();
        
        */
        
        // 在Web应用中可通过HttpServlet的getRemoteIp方法获取  
        String remoteIp = "127.159.152.164";  
        int hashCode = remoteIp.hashCode();  
        System.out.println(hashCode);
        System.out.println(hashCode % 3);
    }
    
    
    public static void testHashSet(){
        System.out.println("-----------hash set------------");
        
        Set<String> hashSet = new HashSet<String>();
        hashSet.add("aaa");
        hashSet.add("bbb");
        hashSet.add("ccc");
        hashSet.add("ddd");
        
        for(Iterator<String> iterator = hashSet.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
        
    }
    
    public static void testLickHashSet(){
        
        System.out.println("------------link haset--------------");
        Set<String> hashSet = new LinkedHashSet<String>();
        hashSet.add("aaa");
        hashSet.add("bbb");
        hashSet.add("ccc");
        hashSet.add("ddd");
        
        for(Iterator<String> iterator = hashSet.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
     
    }
    
    
    public static void testTreeSet(){
        
        System.out.println("------------TreeSet haset--------------");
        Set<String> hashSet = new TreeSet();
        hashSet.add("aaa");
        hashSet.add("bbb");
        hashSet.add("ccc");
        hashSet.add("ddd");
        
        for(Iterator<String> iterator = hashSet.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
        
    }
}
