/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

/**
 *
 * @author zengzw
 * @date 2017年8月11日
 */
public class LtsTask {


    public void autoRun() {
        System.out.println(".....lstRun.....");
    }

    public static void main(String[] args)
            throws Exception {

        // We want this for failover on the slaves
        // props.put(“autoReconnect”, ”true”);    // We want to load balance between the slaves
        // props.put(“roundRobinLoadBalance”, ”true”);
        // props.put(“user”, ”foo”);    props.put(“password”, ”bar”);
        //    // Looks like a normal MySQL JDBC url, with a
        // comma-separated list of hosts, the first
        // being the ’master’, the rest being any number
        // of slaves that the driver will load balance against    //
        // Connection conn =        driver.connect(“jdbc:mysql:replication://master,slave1,slave2,slave3/test”,            props);
        //    // Perform read/write work on the master    // by setting the read-only flag to ”false”    //
        // conn.setReadOnly(false);
        //  conn.setAutoCommit(false);
        // conn.createStatement().executeUpdate(“UPDATE some_table ….”);
        // conn.commit();    //
        // Now, do a query from a slave, the driver automatically picks one    // from the list    //
        //  conn.setReadOnly(true);
        // ResultSet rs =      conn.createStatement().executeQuery(“SELECT a,b FROM alt_table”);     …….  }
        // }


    }

}