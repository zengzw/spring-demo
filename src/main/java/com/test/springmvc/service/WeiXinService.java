/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.service;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.test.springmvc.constants.WeiXinConstants;
import com.test.springmvc.constants.enums.EnumMessageType;
import com.test.springmvc.model.weixin.AccessToken;
import com.test.springmvc.model.weixin.request.TextMessage;
import com.test.springmvc.utils.HttpUtils;
import com.test.springmvc.utils.MessageUtil;
import com.test.springmvc.utils.WeiXinUtils;
import com.test.springmvc.utils.weixin.BuildMenuItemUtils;

/**
 *
 * @author zengzw
 * @date 2016年12月19日
 */

@Service
public class WeiXinService {


    /**
     * 获取AccessToken
     * 
     * @return
     */
    public AccessToken getAccessToken(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("grant_type", "client_credential");
        params.put("appid", WeiXinUtils.appId);
        params.put("secret",WeiXinUtils.appsecret);

        String responeString = HttpUtils.doGet(WeiXinConstants.ACCESS_TOKEN_URL, params);

        if(StringUtils.isEmpty(responeString)){
            return new AccessToken();
        }

        return JSON.parseObject(responeString,AccessToken.class);
    }



    /**
     *  获取微信服务器IP地址
     * @return
     */
    public String getCallbackIp(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", WeiXinUtils.access_token);

        String responeString = HttpUtils.doGet(WeiXinConstants.GET_SERVICE_IP_URL, params);
        return responeString;
    }


    /**
     *  获取用户列表
     *  
     * @return
     */
    public String getUserList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", WeiXinUtils.access_token);
        params.put("next_openid", "");

        String responeString = HttpUtils.doGet(WeiXinConstants.GET_USER_LIST, params);
        return responeString;
    }


    /**
     *  获取用户列表
     *  
     * @return
     */
    public String getUserInfo(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", WeiXinUtils.access_token);
        params.put("openid", WeiXinUtils.user_open_id);
        params.put("lang", "zh_CN");

        String responeString = HttpUtils.doGet(WeiXinConstants.GET_USER_INFO, params);
        return responeString;
    }


    
    /**
     * 创建菜单
     * @return
     */
    public String createMenuItem(){
        String menu = BuildMenuItemUtils.createItem();
        String responeString = HttpUtils.doPostJson(WeiXinConstants.CREATE_MENU+"?access_token="+WeiXinUtils.access_token, menu);
        
        System.out.println("---"+responeString);
        return responeString;
        
    }
    
    
    /**
     * 创建菜单
     * @return
     */
    public String createItemScancodeWaitmsg(){
        String menu = BuildMenuItemUtils.createItemScancodeWaitmsg();
        String responeString = HttpUtils.doPostJson(WeiXinConstants.CREATE_MENU+"?access_token="+WeiXinUtils.access_token, menu);
        
        System.out.println("---"+responeString);
        return responeString;
        
    }
    
    
    
    
    
    /**
     * 删除菜单
     * @return
     */
    public String deleteMenuItem(){
        String responeString = HttpUtils.doPostJson(WeiXinConstants.DELETE_MENU+"?access_token="+WeiXinUtils.access_token, "");
        
        System.out.println("---"+responeString);
        return responeString;
        
    }
    
    
    
    /**
     * 处理微信请求过来
     * 
     * @param inputStream
     * @throws Exception 
     * @return xml string
     */
    public String handleRequest(InputStream inputStream) throws Exception{
        String responseMesage = "";

        try{
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(inputStream);
            System.out.println("--接收到的消息:"+JSON.toJSONString(requestMap));

            String toUserName = requestMap.get("ToUserName"); //开发者微信号
            String fromUserName= requestMap.get("FromUserName"); //发送方帐号（一个OpenID）
            String createTime = requestMap.get("CreateTime"); //消息创建时间
            String msgType = requestMap.get("MsgType"); //消息创建时间
            String content = requestMap.get("Content"); //消息内容
            String msgId = requestMap.get("MsgId"); //消息ID
            String event = requestMap.get("Event");// 事件
            String eventKey = requestMap.get("EventKey");// 事件Code


            //普通消息
            if(msgType.equals(EnumMessageType.text.getType())){
                System.out.println("---text 消息");
                TextMessage message = new TextMessage();
                if(content.equals("1")){
                    message = buildOneTextMessage(toUserName, fromUserName,"Hi,Kelly! Love,Love,Love U");
                }else if(content.equals("2")){
                    message = buildOneTextMessage(toUserName,fromUserName,"无聊是吧？学习运营！\n https://www.zhihu.com/question/26761091 \n 加了个油！");
                }else if(content.equals("3")){
                    message = buildOneTextMessage(toUserName,fromUserName,"世界那么大，到处都可以走走啦！");
                }else{
                    message  = buildTextMessage(toUserName, fromUserName);
                }
                responseMesage = MessageUtil.textMessageToXml(message);
            }
            //事件
            else if(msgType.equals(EnumMessageType.Event.getType())){ 
                
                System.out.println("---事件");
                if(event.equals("CLICK") && eventKey.equals("V1001_GOOD")){
                   TextMessage    message = buildOneTextMessage(toUserName, fromUserName,"谢谢您的称赞！收下啦！撒花！！！");
                   responseMesage = MessageUtil.textMessageToXml(message);
                }
                
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return responseMesage;
    }


    //创建普通消息
    private  TextMessage buildTextMessage(String from,String to){
        // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义            
        StringBuffer contentMsg = new StringBuffer();  
        contentMsg.append("欢迎进入小5的世界").append("\n");  
        contentMsg.append("您好，我是机器人小5，请回复数字选择服务：").append("\n\n");  
        contentMsg.append("1  来点惊喜").append("\n");  
        contentMsg.append("2  工作无聊怎么玩？").append("\n");  
        contentMsg.append("3  周末去哪玩？").append("\n");  
        contentMsg.append("点击查看 <a href=\"http://www.baidu.com\">帮助手册</a>");  

        TextMessage message = new TextMessage();
        message.setFromUserName(from);
        message.setToUserName(to);
        message.setMsgType(EnumMessageType.text.getType());
        message.setCreateTime(new Date().getTime());
        message.setContent(contentMsg.toString());

        return message;
    }

    //创建普通消息
    private  TextMessage buildOneTextMessage(String from,String to,String msg){
        // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义            
        StringBuffer contentMsg = new StringBuffer();  
        contentMsg.append(msg).append("\n");  

        TextMessage message = new TextMessage();
        message.setFromUserName(from);
        message.setToUserName(to);
        message.setMsgType(EnumMessageType.text.getType());
        message.setCreateTime(new Date().getTime());
        message.setContent(contentMsg.toString());

        return message;
    }
    
    
    
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        buffer.toString();
    }
}
