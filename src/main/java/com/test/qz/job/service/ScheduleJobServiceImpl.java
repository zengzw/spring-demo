/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.qz.job.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.test.qz.job.QuartzJob;
import com.test.qz.job.QuartzJobDisallowConcurrent;
import com.test.qz.job.bean.ScheduleJob;
import com.test.qz.job.util.TaskJobUtils;

/**
 *
 * @author zengzw
 * @date 2016年4月6日
 */
@Service
public class ScheduleJobServiceImpl implements IScheduleJobService{

    private Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Autowired
    SchedulerFactoryBean factoryBean;

    @Override
    public void addJob(ScheduleJob job) throws SchedulerException {
        if(job == null 
                /* || job.getJobStatus().equals(TaskJobUtils.STATUS_RUNNING)*/){
            logger.info("{},任务已经存在，而且在运行！",job.getJobName());
            return;
        }

        Scheduler scheduler = factoryBean.getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        boolean isExit = scheduler.checkExists(triggerKey);
        System.err.println("job exists:"+isExit);

        //不存在创建新的job
        if(cronTrigger == null){
            //是有状态job 还是无 无状态的job
            Class clazz = (TaskJobUtils.CONCURRENT_IS.equals(job.getIsConcurrent())) 
                    ? QuartzJob.class : QuartzJobDisallowConcurrent.class;

            //创建一个job明细
            JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(jobKey)
                    .build();
            //存储job实例
            jobDetail.getJobDataMap().put("scheduleJob", job);



            //创建trigger
            //判断表达式是否合法
            boolean isRightful = CronExpression.isValidExpression(job.getJobCronExpression());
            if(!isRightful){
                throw new SchedulerException("表示不合法:"+job.getJobCronExpression());
            }

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getJobCronExpression()).withMisfireHandlingInstructionIgnoreMisfires();
            cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .forJob(jobDetail)
                    .withSchedule(scheduleBuilder).build();



            scheduler.scheduleJob(jobDetail,cronTrigger);
        }//存在，则更新job、
        else{
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getJobCronExpression());
            cronTrigger = cronTrigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.rescheduleJob(triggerKey, cronTrigger);
        }

        System.out.println("====== 添加任务成功 ========");
    }

    @Override
    public void update(ScheduleJob job) throws SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),job.getJobGroup());


        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(job.getJobCronExpression());

        cronTrigger = cronTrigger.getTriggerBuilder()
                .withIdentity(triggerKey)
                .withSchedule(builder)
                .build();
        scheduler.rescheduleJob(triggerKey, cronTrigger);

        System.out.println("====== 修改任务成功 ========");
    }

    @Override
    public void remove(ScheduleJob job) throws SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.resumeJob(jobKey);

        System.out.println("====== 删除任务成功 ========");
    }

    @Override
    public void stop(ScheduleJob job) throws SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
        scheduler.pauseJob(jobKey);

        System.out.println("====== 停止任务成功 ========");
    }

    @Override
    public void resume(ScheduleJob job) throws SchedulerException {

        Scheduler scheduler = factoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.resumeJob(jobKey);

        System.out.println("====== 恢复任务成功 ========");
    }

    @Override
    public List<ScheduleJob> getAll() throws SchedulerException {

        Scheduler scheduler = factoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJob job = new ScheduleJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setJobDesc("触发器:" + trigger.getKey());
                
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setJobCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }

        return jobList;

    }

    @Override
    public void runJob(ScheduleJob job) throws SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
        scheduler.triggerJob(jobKey);

        System.out.println("====== 立即执行任务成功 ========");
    }

    /**
     * trigger各状态说明：

         None：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除 
         NORMAL:正常状态
         PAUSED：暂停状态 
         COMPLETE：触发器完成，但是任务可能还正在执行中 
         BLOCKED：线程阻塞状态 
         ERROR：出现错误
     */
    @Override
    public List<ScheduleJob> getExecutingJob() throws SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();
        List<JobExecutionContext> lstJobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
        System.err.println("--------"+lstJobExecutionContexts.size());
        List<ScheduleJob> lstsJobs = new ArrayList<ScheduleJob>();

        for(JobExecutionContext context : lstJobExecutionContexts){
            ScheduleJob scheduleJob = new ScheduleJob();

            JobDetail jobDetail = context.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = context.getTrigger();

            scheduleJob.setJobName(jobKey.getName());
            scheduleJob.setJobGroup(jobKey.getGroup());
            scheduleJob.setBeanClass(jobDetail.getJobClass().getName());

            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            scheduleJob.setJobStatus(triggerState.name());

            if(trigger instanceof CronTrigger){
                CronTrigger cronTrigger = (CronTrigger)trigger;
                String croExpression = cronTrigger.getCronExpression();

                scheduleJob.setJobCronExpression(croExpression);
            }

            lstsJobs.add(scheduleJob);
        }

        return lstsJobs;
    }

}
