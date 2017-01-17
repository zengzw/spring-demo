/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.storm.bolt;

import java.io.File;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * 处理特殊字符
 * 
 * @author zengzw
 * @date 2017年1月17日
 */
public class ReplaceBolt extends BaseRichBolt{

    /**
     * 
     */
    private static final long serialVersionUID = -4705265102013029021L;
    
    OutputCollector outputCollector;
    
    @Override
    public void execute(Tuple tuple) {
        
        String contentx = tuple.getStringByField("sentence");
        contentx = contentx.replaceAll("\"", "").replace("\\.", "").replace("\\:", "");
        outputCollector.emit(new Values(contentx));
        
        outputCollector.ack(tuple);
    }

    @Override
    public void prepare(Map map, TopologyContext context, OutputCollector collector) {
        this.outputCollector = collector;
    }
    

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("replaceFiled"));
    }

}
