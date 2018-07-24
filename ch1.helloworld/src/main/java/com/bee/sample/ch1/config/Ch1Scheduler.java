package com.bee.sample.ch1.config;

import java.io.IOException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bee.sample.ch1.dojob.AddBooksJob;
import com.bee.sample.ch1.job.MyBaseJob;

@Service
public class Ch1Scheduler {

	
	@Autowired
	@Qualifier("scheduler")
	Scheduler scheduler;
	
//	@Autowired
//	AddBooksJob addBooksJob;
	
	public synchronized void start() throws SchedulerException, IOException {
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	public void zuZhuang(Class<? extends MyBaseJob> bkclass) throws SchedulerException, IOException {
		proJobDetile(bkclass);
	}
	
	public void proJobDetile(Class<? extends MyBaseJob> bkclass) throws SchedulerException {
		JobDetail jobdetile=JobBuilder.newJob(bkclass).withIdentity(bkclass.getSimpleName(), "group1").storeDurably().build();
		proTrigger(bkclass,jobdetile);
	}
	
	public void proTrigger(Class<? extends MyBaseJob> bkclass,JobDetail jd) throws SchedulerException {
		
		TriggerKey triggerKey=new TriggerKey(bkclass.getSimpleName(), "group1");
		boolean isExistTriggerKey=scheduler.checkExists(triggerKey);
		
		if(isExistTriggerKey) {
			scheduler.resumeTrigger(triggerKey);// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
		}
		
		CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity(bkclass.getSimpleName(), "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")).build();
		scheduler.scheduleJob(jd, trigger);
	}
	
	
	
	public void zuZhuang2(Class<? extends MyBaseJob> bkclass,String topic) throws SchedulerException, IOException {
		proJobDetile2(bkclass,topic);
	}
	
	public void proJobDetile2(Class<? extends MyBaseJob> bkclass,String topic) throws SchedulerException {
		JobDetail jobdetile=JobBuilder.newJob(bkclass).withIdentity(bkclass.getSimpleName(), "group1").storeDurably().build();
		jobdetile.getJobDataMap().put("topic", topic);
		proTrigger(bkclass,jobdetile);
	}
	
	
	
	
	
	
	
	
}
