/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.model.weixin.response;

/**
 * 用于列表
 * 
 * @author zengzw
 * @date 2016年12月19日
 */
public class RespUserList {

    private int total;
    
    private int count;

    private UserOpenIdVo data;

    private String next_openid;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UserOpenIdVo getData() {
        return data;
    }

    public void setData(UserOpenIdVo data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

}
