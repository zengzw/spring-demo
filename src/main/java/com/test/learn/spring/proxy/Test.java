/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.spring.proxy;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactoryBean;

import com.test.springmvc.model.User;

/**
 *
 * @author zengzw
 * @date 2017年2月27日
 */
public class Test {
    
    public static void main(String[] args) {
        //前置通知
        Advice beforeAdvice = new TicketBeforeAdvice();
        
        //环绕通知
        Advice aroundAdvice = new TicketServiceAroundAdvice(); 
        
        RailwayStation station = new RailwayStation();
        
        //2.创建ProxyFactoryBean,用以创建指定对象的Proxy对象  
        ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        factoryBean.setInterfaces(ITicketService.class);
        factoryBean.setTarget(station);
        //5.使用JDK基于接口实现机制的动态代理生成Proxy代理对象，如果想使用CGLIB，需要将这个flag设置成true  
        factoryBean.setProxyTargetClass(true);
        
        //6. 添加不同的Advice  
        factoryBean.addAdvice(beforeAdvice);
        factoryBean.addAdvice(aroundAdvice);
        
        //7通过ProxyFactoryBean生成Proxy对象  
        ITicketService service = (ITicketService)factoryBean.getObject();
        service.sellTicket("王者归来");
        
        User user = new User();
        user.setId(22);
        user.setName("userName");
        service.inquire(user);
        
    }

}
