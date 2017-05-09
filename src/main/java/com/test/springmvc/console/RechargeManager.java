/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.console;

import java.util.List;

/**
 *
 * @author zengzw
 * @date 2017年2月13日
 */
public class RechargeManager {

    public static void reacharge(String orderNo) throws InterruptedException{
        List<String> list = ServiceProviderManager.get();
        String url = list.remove(0);
        System.out.println("--------->request orderNo:"+orderNo+" : current rquest url:"+url);


        int result = get(url); 
        if(result == 0){
            ServiceProviderManager.remove();
            System.out.println(orderNo+"----成功，移除local");
        }else{
            System.out.println(orderNo+"----重新设置");
            ServiceProviderManager.add(list);
            reacharge(orderNo);
        }


    }
    
    


    private static int  get(String url) throws InterruptedException{
        Thread.sleep(2000); //模拟异步请求
        if(url.equals("loop")){
            return 1;
        }

        return 0;
    }

}
