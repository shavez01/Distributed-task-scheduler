package com.scheduler.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

import com.scheduler.job.HttpCallJob;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {
 private final Scheduler scheduler;
 public void schedule(String name,String cron,String url,int i) throws Exception{
	 JobDetail job = JobBuilder.newJob(HttpCallJob.class)
		        .withIdentity(name)
		        .usingJobData("url", url)
		        .usingJobData("maxAttempts", String.valueOf(i))
		        .build();
  Trigger trigger = TriggerBuilder.newTrigger()
    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
    .build();
  scheduler.scheduleJob(job,trigger);
 }
}
