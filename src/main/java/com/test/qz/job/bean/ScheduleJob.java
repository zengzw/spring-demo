/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.qz.job.bean;

/**
 * 任务类的实体对象
 * 
 * @author zengzw
 * @date 2016年4月6日
 */
public class ScheduleJob {
    
    /**
     * 任务Id
     */
    private String jobId;
    
    /**
     * 任务名称
     */
    private String jobName;
    
    /**
     * 任务组
     */
    private String jobGroup;
    
    /**
     * 任务状态
     */
    private String jobStatus;
    
    /**
     * 任务时间
     */
    private String jobCronExpression;
    
    /**
     *  任务描述
     */
    private String jobDesc;
    
    /**
     *  执行的bean类  beanClass 与 springId 至少要有一个存在
     */
    private String beanClass;
    
    /**
     * spring Bean
     */
    private String springId;
    
    /**
     * 方法名
     */
    private String methedName;
    
    /** 
     * 任务是否有状态 
     */  
    private String isConcurrent;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobCronExpression() {
        return jobCronExpression;
    }

    public void setJobCronExpression(String jobCronExpression) {
        this.jobCronExpression = jobCronExpression;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getSpringId() {
        return springId;
    }

    public void setSpringId(String springId) {
        this.springId = springId;
    }

    public String getMethedName() {
        return methedName;
    }

    public void setMethedName(String methedName) {
        this.methedName = methedName;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }  
    
    

}
