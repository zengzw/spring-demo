/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.qz.job;

import java.lang.reflect.InvocationTargetException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.test.qz.job.bean.ScheduleJob;
import com.test.qz.job.util.TaskJobUtils;

/**
 * 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作 
 * 
 * @author zengzw
 * @date 2016年4月6日
 */
@DisallowConcurrentExecution
public class QuartzJobDisallowConcurrent implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("\t ##############任务开始执行 QuartzJobDisallowConcurrent###################");
        ScheduleJob job = (ScheduleJob)context.getJobDetail().getJobDataMap().get("scheduleJob");
        try {
            
            TaskJobUtils.invokeMethod(job);
            
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
