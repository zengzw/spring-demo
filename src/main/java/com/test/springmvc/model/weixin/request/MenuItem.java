/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.model.weixin.request;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zengzw
 * @date 2016年12月21日
 */
public class MenuItem {
    
    public List<MenuItem> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<MenuItem> sub_button) {
        this.sub_button = sub_button;
    }

    private String type; //菜单的响应动作类型
    
    private String name; //菜单名称
    
    private String key; //菜单key，会传送到开发者接口
   
    private String url; //链接地址
    
    private String media_id; //用新增永久素材接口返回的合法media_id

    /*
     * 二级菜单
     */
    List<MenuItem> sub_button;
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
