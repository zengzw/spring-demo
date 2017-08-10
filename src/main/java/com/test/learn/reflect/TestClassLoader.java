/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.reflect;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;


/**
 *
 * @author zengzw
 * @date 2017年6月30日
 */
public class TestClassLoader {


    public void test(){
        System.out.println("---test---");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        String packageName = "com.test.learn.reflect";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 从包名获取 URL 类型的资源
        Enumeration<URL> urls = classLoader.getResources(packageName.replace(".", "/"));
        // 遍历 URL 资源
        while(urls.hasMoreElements()){
            URL url =  urls.nextElement();
            if(url != null){
                String protocol = url.getProtocol();
                if(protocol.equals("file")){
                    String packagePath = url.getPath().replaceAll("20%", "");
                    
                    
                    System.out.println(packagePath);
                    //获取包路径下的class文件 和 目录
                    File[] files = new File(packagePath).listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File file) {
                           
                            return ((file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
                        }
                    });
                    
                    for(File file : files){
                        String fileName = file.getName();
                        if(file.isFile()){
                            String className = packageName + File.separator + fileName.substring(0,fileName.lastIndexOf("."));
                            Class<?> clz = Class.forName(className);
                        }
                    }
                    
                }
            }
        }
    }

}
