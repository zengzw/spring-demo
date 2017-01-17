/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.storm.bolt;

import java.util.HashMap;
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
public class CountBolt extends BaseRichBolt{
    /**
     * 
     */
    private static final long serialVersionUID = 2105319564458243548L;

    OutputCollector collector;

    private Map<String, Integer> countMap = new HashMap<String, Integer>();


    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getString(0);
        Integer count = 0;
        if(countMap.containsKey(word)){
            count = countMap.get(word);
        }
        count++;
        countMap.put(word, count);

        collector.emit(new Values(word,count));
    }

    @Override
    public void prepare(Map map, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word","count"));
    }

}
