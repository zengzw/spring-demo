/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author zengzw
 * @date 2017年5月22日
 */
public class SubJob extends SuperJob{

    
    public static void main(String[] args) {
        
      /*  ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        
        service.scheduleAtFixedRate(new Runnable() {
            
            @Override
            public void run() {
              System.out.println("run.....");
                
            }
        }, 10, 1, TimeUnit.SECONDS);*/
    	
    	
    	System.out.println(3 | 3);
    }
   
}
