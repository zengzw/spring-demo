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
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 *
 * @author zengzw
 * @date 2017年1月17日
 */
public class SplitBolt extends BaseRichBolt{

    /**
     * 
     */
    private static final long serialVersionUID = -8222338849105333421L;
    OutputCollector collector;
    
    @Override
    public void execute(Tuple tuple) {
        String sentence = tuple.getStringByField("replaceFiled");
        String[] words = sentence.split(" ");
        for(String word:words){
            collector.emit(new Values(word));
        }
    }

    @Override
    public void prepare(Map map, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("replace"));
    }

}
