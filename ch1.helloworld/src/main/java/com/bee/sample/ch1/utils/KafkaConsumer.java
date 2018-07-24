package com.bee.sample.ch1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ch1.ignite.dao.IgBookDao;
import com.bee.sample.ch1.ignite.vo.Book;

@Component
public class KafkaConsumer {
	
	@Autowired
	IgBookDao igBookDao;

	@KafkaListener(topics="${recive.order.topic}",groupId="grouptest1")
	public void reciveMessage(String content) {
		
		System.out.println("开始输出kafka接收到的数据：-------");
		System.out.println(content);
		System.out.println("输出完成");
		
		
		Book book = JSON.parseObject(content, Book.class);
		igBookDao.addIdBook(book);
		
		
		
		
	}
}
