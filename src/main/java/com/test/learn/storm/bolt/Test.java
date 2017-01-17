/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.storm.bolt;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author zengzw
 * @date 2017年1月17日
 */
public class Test {
    
    public static void main(String[] args) {
        File file = new File("D://test.txt");
        //1484634252485
        //1484634323072

        System.out.println(file.lastModified());
        try {
            List<String> lstString = FileUtils.readLines(file);
            for(String s:lstString){
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
