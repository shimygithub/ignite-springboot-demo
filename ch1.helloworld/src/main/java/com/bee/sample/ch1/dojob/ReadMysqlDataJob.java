package com.bee.sample.ch1.dojob;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ch1.ignite.vo.Book;
import com.bee.sample.ch1.job.MyBaseJob;
import com.bee.sample.ch1.mapper.BookMapper;
import com.bee.sample.ch1.utils.ConfDataUtil;
import com.bee.sample.ch1.utils.KafkaSender;
import com.bee.sample.ch1.vo.Book1;
import com.mysql.cj.MysqlConnection;

public class ReadMysqlDataJob extends MyBaseJob{
	
	@Autowired
	@Qualifier("igCache")
	IgniteCache<String, Book> ic;
	
	@Autowired
	@Qualifier("ignite")
	Ignite ignite;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	KafkaSender kafkaSender;
	
	@Autowired
	ConfDataUtil confDataUtil;
	
	
	public void igTest() {
		
//		ic.clear();
		
		List<Book1> book1list=bookMapper.queryBooks();
		
//		Map<Integer, Book> map=new HashMap<>();
		Random rd=new Random();
		for (Book1 book1 : book1list) {
//			System.out.println("看见你倒是可能开始对尼克斯bookId= "+book1.getBookId()+"   bookPrice= " + book1.getBookPrice()+"   bookName= " + book1.getBookName()+"  bookAuther= "+book1.getBookAuther());
			
			
//			Book book=new Book(1000+rd.nextInt(10000), book1.getBookPrice(), book1.getBookName(), book1.getBookAuther());

			
//			map.put(book.getBookId(), book);
			
			kafkaSender.sendMessage(confDataUtil.getSenderOrderTopic(), JSON.toJSONString(book1));
		}
		
		
		
////		ic.putAllAsync(map);
//		
////		System.out.println("map已存入ignite");
//		
//		List<List<?>> list=ic.query(new SqlFieldsQuery("select * from Book")).getAll();
//		System.out.println("输出存入的book集合内容----------------------------");
//		for (List<?> bookList : list) {
//			
//			System.out.println("bookId= "+bookList.get(0)+"   bookPrice= " + bookList.get(1)+"   bookName= " + bookList.get(2)+"  bookAuther= "+bookList.get(3));
//			
//		}
//		
//		System.out.println("输出完毕");
////		ic.close();
////		ignite.close();
		
	}

	@Override
	public void addData() {
		// TODO Auto-generated method stub
		igTest();
	}

}
