/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.disignpattern.state;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author zengzw
 * @date 2017年2月16日
 */
public class Context {

    private List<State> states = new ArrayList<State>();


    public void setState(State...stas){
        for(int i = 0; i < stas.length; i++){
            this.states.add(stas[i]);
        }  
    }

    /**
     * 返回所有状态
     * 
     * @return
     */
    public List<State> supportState(){
        return this.states;
    }



    /**
     * 判断是否支持 从 状态A 改 位状态B
     * @param updateState
     * @return
     */
    public boolean support(int updateState){
        boolean support = false;

        for(int i = 0;  i < states.size(); i++){
            State state = states.get(i);

            if(state.current() == updateState){
                support = true;
                break;
            }
        }   

        return support;
    }


}
