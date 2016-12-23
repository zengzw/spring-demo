/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.constants.enums;


/**
 * http://mp.weixin.qq.com/wiki/10/0234e39a2025342c17a7d23595c6b40a.html
 * 
 * @author zengzw
 * @date 2016年12月20日
 */
public enum EnumMenuItemType{

    /**
     *  点击事件
     */
    click("click"),

    /**
     *  跳转 URL
     */
    view("view"),

    /**
     * 扫码推事件
     */
    scancode_push("scancode_push"),

    /**
     * 扫码推事件且弹出“消息接收中”提示框
     */
    scancode_waitmsg("scancode_waitmsg"),

    /**
     * 弹出系统拍照发图
     */
    pic_sysphoto("pic_sysphoto"),
    /**
     * 弹出拍照或者相册发图
     */
    pic_photo_or_album("pic_photo_or_album"),

    /**
     * 弹出微信相册发图器
     */
    pic_weixin("pic_weixin"),

    /**
     * 弹出地理位置选择器
     */
    location_select("location_select"),

    /**
     * 下发消息（除文本消息）
     */
    media_id("location_select"),

    /**
     * ：跳转图文消息URL
     */
    view_limited("view_limited");

    private String type;

    EnumMenuItemType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
