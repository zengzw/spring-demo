/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.storm.topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import com.test.learn.storm.bolt.CountBolt;
import com.test.learn.storm.bolt.PrintBolt;
import com.test.learn.storm.bolt.ReplaceBolt;
import com.test.learn.storm.bolt.SplitBolt;
import com.test.learn.storm.spout.WordCountSpout;

/**
 *
 * @author zengzw
 * @date 2017年1月17日
 */
public class WordCountTopology {

    public static void main(String[] args) throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();  
        builder.setSpout("wordCountSpout", new WordCountSpout(),1);
        builder.setBolt("replaceBolt",new ReplaceBolt(),1).shuffleGrouping("wordCountSpout");
        builder.setBolt("splitBolt", new SplitBolt(),1).shuffleGrouping("replaceBolt");
      builder.setBolt("countBolt", new CountBolt(),1).fieldsGrouping("splitBolt", new Fields("split"));
        builder.setBolt("print",new PrintBolt()).shuffleGrouping("countBolt");

        
        Config config = new Config();  
        config.setDebug(false);  
        config.put("filePath", "D://test.txt");
        
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("WordCount", config, builder.createTopology());
        
        
    }
}
