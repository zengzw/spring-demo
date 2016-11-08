/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.qz.job.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.test.qz.job.bean.ScheduleJob;

/**
 *
 * @author zengzw
 * @date 2016年4月6日
 */
public interface IScheduleJobService {
    

    /**
     * 添加任务
     * 
     * @param job
     * @throws SchedulerException
     */
    public void addJob(ScheduleJob job) throws SchedulerException;
    
    
    /**
     * 修改任务
     * @param job
     * @throws SchedulerException
     */
    public void update(ScheduleJob job)  throws SchedulerException;
    
    
    /**
     * 删除任务
     * 
     * @param job
     * @throws SchedulerException
     */
    public void remove(ScheduleJob job)  throws SchedulerException;
    
    
    /**
     * 停止任务
     * 
     * @param job
     * @throws SchedulerException
     */
    public void stop(ScheduleJob job) throws SchedulerException;
    
    
    /**
     * 立即执行任务
     * 
     * @param job
     * @throws SchedulerException
     */
    public void runJob(ScheduleJob job) throws SchedulerException;
    
    
    /**
     * 恢复任务
     * 
     * @param job
     * @throws SchedulerException
     */
    public void resume(ScheduleJob job)  throws SchedulerException;
    
    
    /**
     * 获取所有执行的任务
     * 
     * @return
     * @throws SchedulerException
     */
    public List<ScheduleJob> getAll() throws SchedulerException;  
    
    
    /**
     * 获取所有执行的任务
     * @return
     * @throws SchedulerException
     */
    public  List<ScheduleJob> getExecutingJob() throws SchedulerException;
        
}
