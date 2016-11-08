/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.springmvc.model.ProductChoiceItemVo;
import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2014年10月11日
 */
@Controller
public class UserController {

    @RequestMapping("/test")
    public String test(User user){
        System.out.println(user.getDateTime());

        return "test";
    }


    @RequestMapping("/product/list")
    @ResponseBody
    public Object list(){

        List<ProductChoiceItemVo> items = new ArrayList<ProductChoiceItemVo>();
        
        for(int i = 0; i<4; i++){
            ProductChoiceItemVo vo = new ProductChoiceItemVo();
            vo.setId(i);
            vo.setDesc("[香港包邮，荷兰奶粉，两罐装]"+i);
            vo.setPrice(200+i);
            vo.setImage("http://img.papago.hk/imagesupload/24190/big/P-126563-001.jpg");
            
            items.add(vo);
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataList", items);
        map.put("responseCode", 1);
        
        return map;
    }

    public static void main(String[] args) {
    }
}
