package com.bee.sample.ch1.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import groovy.swing.factory.TDFactory;

@Configuration
public class SchedulerConfig {
	
	@Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();

        jobFactory.setApplicationContext(applicationContext);

        return jobFactory;
    }

    @Bean(name="schedulerFactoryBean")
	public SchedulerFactoryBean getSchedulerFactory(JobFactory jobFactory) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(jobFactory);
		return factory;
		
	}
	
	@Bean("scheduler")
	public Scheduler getScheduler(SchedulerFactoryBean factory) throws SchedulerException {
		return factory.getScheduler();
		
	}
	
	
}
