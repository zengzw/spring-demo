/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.learn.threadpool;

import java.io.Serializable;

/**
 *
 * @author zengzw
 * @date 2017年6月1日
 */
public class ThreadPoolTask implements Runnable, Serializable{

    private static final long serialVersionUID = 0;
    private static int consumeTaskSleepTime = 2000;
    // 保存任务所需要的数据
    private Object threadPoolTaskData;

    ThreadPoolTask(Object tasks)
    {
        this.threadPoolTaskData = tasks;
    }

    public void run()
    {
        // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
        System.out.print(Thread.currentThread().getName());
        System.out.println("，execute:" + threadPoolTaskData);

        try
        {
            // //便于观察，等待一段时间
            Thread.sleep(consumeTaskSleepTime);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        threadPoolTaskData = null;
    }

    public Object getTask(){
        return this.threadPoolTaskData;
    }
}
