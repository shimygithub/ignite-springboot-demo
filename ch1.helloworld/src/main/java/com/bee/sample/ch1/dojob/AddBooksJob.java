package com.bee.sample.ch1.dojob;

import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bee.sample.ch1.job.MyBaseJob;
import com.bee.sample.ch1.mapper.BookMapper;
import com.bee.sample.ch1.vo.Book1;

//@Component
public class AddBooksJob extends MyBaseJob{
	
	@Autowired
	private BookMapper bookMapper;



		@Override
		public void addData() {

//			UUID uuid=UUID.randomUUID();
			String bookId=UUID.randomUUID().toString();
			Book1 book=new Book1(bookId,Math.random()*1000,"书名","作者");
			System.out.println("--成熟程度上--"+bookMapper);
			bookMapper.addBook(book);
			System.out.println("bookId为："+bookId+"添加完成");
		}
		
		
		
		

}
