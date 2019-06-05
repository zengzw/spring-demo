package com.test.learn.chain;

import com.test.springmvc.model.User;

/**
 * @author zengzw
 * @date 2019-04-25 17:10:52
 */
public class SmsA implements ISmsFilter {
    @Override
    public boolean filter(User user, SmsFilterChain chain) {
        System.out.println("------A return false");
        boolean result = chain.doFilter(user);
        if(result == true){
            chain.lstError.add("B出错了");
            return true;
        }
        return false;
    }
}
