package com.bee.sample.ch1.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bee.sample.ch1.mapper.BookMapper;
import com.bee.sample.ch1.vo.Book1;


@Controller
public class MysqlTest1 {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	@RequestMapping("/mysqltest1")
	@ResponseBody
	public void mysqlTest1(HttpServletResponse response) {
		PrintWriter p=null;
		try {
			p=response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		bookMapper.addBook(new Book1("1", 143.5, "哈哈哈", "有人"));
		bookMapper.addBook(new Book1("2", 438.5, "哈哈哈2", "有人2"));
		bookMapper.addBook(new Book1("3", 563.5, "哈哈哈3", "有人3"));
		
		
		List<Book1> booklist=bookMapper.queryBooks();
		
		for (Book1 book : booklist) {
			p.println(book.getBookId()+"   "+book.getBookPrice()+"   "+book.getBookName()+"   "+book.getBookAuther()+"   ");
		}
		
	}
	

}
