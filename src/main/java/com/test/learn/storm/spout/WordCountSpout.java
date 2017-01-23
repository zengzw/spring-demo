/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.storm.spout;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 *
 * @author zengzw
 * @date 2017年1月17日
 */
public class WordCountSpout extends BaseRichSpout {

    /**
     * 
     */
    private static final long serialVersionUID = -297933909439798448L;



    SpoutOutputCollector collector;

    
    String filePath;//文件路径


    /**
     * 
     */
    @Override
    public void open(Map map, TopologyContext contenxt, SpoutOutputCollector collector) {
        this.collector = collector;
        filePath = (String)map.get("filePath");
    }


    /**
     * Tuple 处理
     */
    @Override
    public void nextTuple() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      
        try {
            File file = new File(filePath);
            List<String> lstString = FileUtils.readLines(file);
            for(String content:lstString){
                collector.emit(new Values(content));
            }
            
            FileUtils.moveFile(file, new File(file.getPath() + System.currentTimeMillis() + ".bak"));
        } catch (IOException e) {
           
        }
    

    }


    
    /**
     * 字段输出
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
       declarer.declare(new Fields("sentence"));
    }

    
    
    @Override
    public void close() {
        System.out.println("close----");
        super.close();
    }
    
    
    @Override
    public void ack(Object msgId) { 
        System.out.println("---ack:"+msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("---fail:"+msgId);
    }

}
