package com.test.learn.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.test.springmvc.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON test
 * @author zengzw
 * @description: ${description}
 * @date 2019-01-16 14:24:17
 */
public class FastJson {

    public static void main(String[] args) {
        List<User> list =  new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("user-name");
        user.setNickName("nick-name");
        list.add(user);

        //object to string
        String json = JSON.toJSONString(list);

        //String to object
        List<User> jsonUser = JSON.parseObject(json,new TypeReference<List<User>>(){});
        System.out.println(jsonUser);
    }
}
