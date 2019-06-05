package com.test.learn.chain;/**
 * @author zengzw
 * @date 2019-04-25 17:10:01
 */

import com.test.springmvc.model.User;

/**
 *
 *
 *
 * @author: zengzw
 *
 * @create: 2019-04-25 17:10:01
 **/
public interface ISmsFilter {

    public boolean filter(User user,SmsFilterChain chain);
}
