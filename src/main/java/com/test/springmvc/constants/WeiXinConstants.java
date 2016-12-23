/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.constants;

/**
 *
 * @author zengzw
 * @date 2016年12月19日
 */
public class WeiXinConstants {

    private static String ROOT_URL = "https://api.weixin.qq.com";


    /**
     * 获取AccessTOken
     */
    public static String ACCESS_TOKEN_URL = ROOT_URL + "/cgi-bin/token";


    /**
     * 获取服务器ID
     */
    public static String GET_SERVICE_IP_URL = ROOT_URL + "/cgi-bin/getcallbackip";


    /**
     * 获取用户列表
     */
    public static String GET_USER_LIST = ROOT_URL + "/cgi-bin/user/get";
    
    
    /**
     * 获取用户信息
     */
    public static String GET_USER_INFO = ROOT_URL + "/cgi-bin/user/info";
    
    
    /**
     * 创建菜单
     */
    public static String CREATE_MENU = ROOT_URL + "/cgi-bin/menu/create";
    
    /**
     *  删除菜单（全部自定义菜单）
     */
    public static String DELETE_MENU = ROOT_URL + "/cgi-bin/menu/delete";

}
