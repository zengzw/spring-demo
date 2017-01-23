/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.storm.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/**
 *
 * @author zengzw
 * @date 2017年1月17日
 */
public class PrintBolt extends BaseRichBolt{

    
    @Override
    public void execute(Tuple tuple) {
        System.out.println("print---------------"+tuple.getString(0)+":"+tuple.getInteger(1));
        
    }

    @Override
    public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
   
        
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        
    }

}
