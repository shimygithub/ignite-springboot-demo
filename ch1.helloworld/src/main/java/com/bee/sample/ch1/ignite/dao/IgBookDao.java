package com.bee.sample.ch1.ignite.dao;

import java.util.List;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bee.sample.ch1.ignite.vo.Book;

@Component
public class IgBookDao {
	
	@Autowired
	@Qualifier("igCache")
	IgniteCache<String, Book> igCache;
	
	public void addIdBook(Book book) {
		
		igCache.putAsync(book.getBookId(), book);
		
	}
	
	
	public List<List<?>> getBookList() {
		List<List<?>> list=igCache.query(new SqlFieldsQuery("select * from Book")).getAll();
		return list;
	}

}
