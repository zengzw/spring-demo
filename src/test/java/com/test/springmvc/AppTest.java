package com.test.springmvc;

import java.util.List;

import org.junit.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.qz.job.bean.ScheduleJob;
import com.test.qz.job.service.IScheduleJobService;
import com.test.qz.job.util.TaskJobUtils;



/**
 * Unit test for simple App.
 */
public class AppTest  extends TestWithSpring
{
    
    @Autowired
    IScheduleJobService scheduleJobService;
    
   @Test
   public void testAdd() throws SchedulerException, InterruptedException{
     
     ScheduleJob job = new ScheduleJob();
       job.setJobName("test-jobName");
       job.setJobGroup("test-jobGroup");
       job.setMethedName("execute");
       job.setJobCronExpression("*/3 * * * * ?");
       job.setBeanClass("com.test.qz.job.execute.JobExecute");       
       job.setJobDesc("测试");
       job.setJobStatus(TaskJobUtils.STATUS_RUNNING);
       
       scheduleJobService.addJob(job);
       Thread.sleep(5000);
       
       scheduleJobService.addJob(job);
       Thread.sleep(5000);
       
       scheduleJobService.stop(job);
       Thread.sleep(5000);
       
       scheduleJobService.resume(job);
       
       while(true){
           System.out.println("all  job count:"+scheduleJobService.getAll().size());
           System.out.println("all executing job count:"+scheduleJobService.getExecutingJob().size());
           Thread.sleep(5000);
       }
   }
}
