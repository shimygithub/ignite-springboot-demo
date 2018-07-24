package com.bee.sample.ch1.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class MyBaseJob implements Job{
	
	public abstract void addData();
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		addData();
	}

}
