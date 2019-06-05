package com.test.learn.chain;

import com.test.springmvc.model.User;

/**
 * @author zengzw
 * @date 2019-04-25 17:10:52
 */
public class SmsC implements ISmsFilter {
    @Override
    public boolean filter(User user, SmsFilterChain chain) {
        System.out.println("------C return true");

        boolean result =  chain.doFilter(user);
        if(result == true){
            chain.lstError.add("C出错了");
            return true;
        }
        return true;
    }
}
