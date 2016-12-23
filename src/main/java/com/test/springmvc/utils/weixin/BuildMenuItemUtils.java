/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.utils.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.test.springmvc.constants.enums.EnumMenuItemType;
import com.test.springmvc.model.weixin.request.MenuItem;

/**
 *
 * @author zengzw
 * @date 2016年12月21日
 */
public class BuildMenuItemUtils {


    public static String createItem(){
        
        List<MenuItem> lstItems = new ArrayList<MenuItem>();
        MenuItem item = new MenuItem();
        item.setName("茶叶");
    /*  item.setType(EnumMenuItemType.click.getType());
        item.setKey("tea");*/
        
        MenuItem item1 = new MenuItem();
        item1.setName("茶铺子");
        item1.setType(EnumMenuItemType.click.getType());
        item1.setKey("store");
        
      
        List<MenuItem> lstSubItems = new ArrayList<MenuItem>();
        MenuItem  subitem= new MenuItem();
        subitem.setName("乌龙茶");
        subitem.setType(EnumMenuItemType.click.getType());
        subitem.setKey("oolong");
        lstSubItems.add(subitem);
        
        item.setSub_button(lstSubItems);
        lstItems.add(item);
        lstItems.add(item1);
        
        
        
        Map<String, Object> mapitem= new HashMap<String,Object>();
        mapitem.put("button",lstItems);
        
        String result = JSON.toJSONString(mapitem);
        System.out.println(result);
        return result;
    }
    
    public static String createItemScancodeWaitmsg(){
        List<MenuItem> lstItems = new ArrayList<MenuItem>();
        
        //第一菜单
        MenuItem item = new MenuItem();
        item.setName("扫码");
        //第一菜单，二级菜单
        MenuItem subitem1 = new MenuItem();
        subitem1.setName("扫码带提示");
        subitem1.setType(EnumMenuItemType.scancode_waitmsg.getType());
        subitem1.setKey("scancode_waitmsg");
        
        MenuItem subitem2 = new MenuItem();
        subitem2.setName("扫码推事件");
        subitem2.setType(EnumMenuItemType.scancode_push.getType());
        subitem2.setKey("scancode_push");
        
        List<MenuItem> lstSubItems = new ArrayList<MenuItem>();
        lstSubItems.add(subitem1);
        lstSubItems.add(subitem2);
        
        //第二个菜单
        MenuItem item1 = new MenuItem();
        item1.setName("发图");
        
        //第二菜单，二级菜单
        MenuItem subitem3 = new MenuItem();
        subitem3.setName("系统拍照发图");
        subitem3.setType(EnumMenuItemType.pic_sysphoto.getType());
        subitem3.setKey("pic_sysphoto");
        
        MenuItem subitem4 = new MenuItem();
        subitem4.setName("拍照或者相册发图");
        subitem4.setType(EnumMenuItemType.pic_photo_or_album.getType());
        subitem4.setKey("pic_photo_or_album");
        
        MenuItem subitem5 = new MenuItem();
        subitem5.setName("微信相册发图");
        subitem5.setType(EnumMenuItemType.pic_weixin.getType());
        subitem5.setKey("pic_weixin");
        
        List<MenuItem> lstSubItems1 = new ArrayList<MenuItem>();
        lstSubItems1.add(subitem3);
        lstSubItems1.add(subitem4);
        lstSubItems1.add(subitem5);
        
        item.setSub_button(lstSubItems);
        item1.setSub_button(lstSubItems1);
        
        MenuItem locationItem = new MenuItem();
        locationItem.setName("发送位置");
        locationItem.setType(EnumMenuItemType.location_select.getType());
        locationItem.setKey("location_select");
        
     /*   MenuItem mediaItem = new MenuItem();
        mediaItem.setName("图片");
        mediaItem.setType(EnumMenuItemType.media_id.getType());
        mediaItem.setKey("media_id");
        
        MenuItem viewItem = new MenuItem();
        viewItem.setName("图文消息");
        viewItem.setType(EnumMenuItemType.view_limited.getType());
        viewItem.setKey("view_limited");
        */
        
        
        lstItems.add(item);
        lstItems.add(item1);
        lstItems.add(locationItem);
        
        
        
        
        Map<String, Object> mapitem= new HashMap<String,Object>();
        mapitem.put("button",lstItems);
        
        String result = JSON.toJSONString(mapitem);
        System.out.println(result);
        return result;
    }
    
    public static void main(String[] args) {
        createItem();
    }

}
