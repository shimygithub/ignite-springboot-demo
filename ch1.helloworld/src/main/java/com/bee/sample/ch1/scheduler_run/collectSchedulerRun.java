package com.bee.sample.ch1.scheduler_run;

import java.util.Set;

import org.quartz.Scheduler;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bee.sample.ch1.config.Ch1Scheduler;
import com.bee.sample.ch1.dojob.AddBooksJob;
import com.bee.sample.ch1.job.MyBaseJob;
import com.bee.sample.ch1.mapper.BookMapper;

@Component
@Order(value = 1)
public class collectSchedulerRun implements CommandLineRunner {

	@Autowired
	private Ch1Scheduler cs;
	@Autowired
	private BookMapper bookMapper;

	@Override
	public void run(String... args) throws Exception {
		
//		AddBooksJob ab=new AddBooksJob();
//		System.out.println("呃呃呃呃呃呃呃呃呃"+ab.bookMapper);
//		ab.bookMapper=bookMapper;
//		System.out.println("呀呀呀呀呀呀"+ab.bookMapper);

		String collectorpackage = "com.bee.sample.ch1.dojob";// 采集任务工作都要写在这个路径下，一个采集一个类
		// 目的是实例化com.cloudwise.project.bausch.job.collector路径下的所有类（通过反射）
		ConfigurationBuilder configBuilder = new ConfigurationBuilder()
				.filterInputsBy(new FilterBuilder().includePackage(collectorpackage))
				.setUrls(ClasspathHelper.forPackage(collectorpackage))
				.setScanners(new TypeAnnotationsScanner(), new MethodAnnotationsScanner(), new SubTypesScanner(false));
		Reflections reflections = new Reflections(configBuilder);// 反射
		Set<Class<? extends MyBaseJob>> clazzs = reflections.getSubTypesOf(MyBaseJob.class);// MyBaseJob是抽象父类
		for (Class<? extends MyBaseJob> clazz : clazzs) {
			cs.zuZhuang(clazz);
		}

		cs.start();

	}

}
