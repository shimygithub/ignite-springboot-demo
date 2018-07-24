package com.bee.sample.ch1.ignite.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.lang.IgniteFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bee.sample.ch1.ignite.vo.Book;

@Controller
public class IgController {
	
	@Autowired
	@Qualifier("igCache")
	IgniteCache<String, Book> ic;
	
//	@Autowired
//	@Qualifier("ignite")
//	Ignite ignite;
	
	@RequestMapping("/igTest")
	@ResponseBody
	public void igTest(HttpServletResponse response) {
		PrintWriter p = null;
		try {
			response.setCharacterEncoding("UTF-8"); 
			p=response.getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book b1=new Book("1", 43.32, "精髓", "史林凡");
		ic.put(b1.getBookId(), b1);
		ic.get(b1.getBookId());
		System.out.println("取出的b1的值为：");
		System.out.println("bookId= "+b1.getBookId()+"   bookPrice= " + b1.getBookPrice()+"   bookName= " + b1.getBookName()+"  bookAuther= "+b1.getBookAuther());
		if(p!=null)
		p.println("bookId= "+b1.getBookId()+"   bookPrice= " + b1.getBookPrice()+"   bookName= " + b1.getBookName()+"  bookAuther= "+b1.getBookAuther());

		
		
		Book b2=new Book("2", 44.32, "精髓2", "史林凡tow");
		Book b3=new Book("3", 45.32, "精髓3", "史林凡three");
		Book b4=new Book("4", 46.32, "精髓4", "史林凡four");
		Book b5=new Book("5", 47.32, "精髓5", "史林凡five");
		Map<String, Book> map=new HashMap<>();
		map.put(b2.getBookId(), b2);
		map.put(b3.getBookId(), b3);
		map.put(b4.getBookId(), b4);
		map.put(b5.getBookId(), b5);
		
		ic.putAllAsync(map);
		
		System.out.println("map已存入ignite");
		
		
		
//		IgniteFuture<Map<Integer, Book>> ifu=ic.getAllAsync(map.keySet());
////		ifu.listen(bk ->{
//////			bk.get()
////		});
//		map=ifu.get();
//		p.println("少时诵诗书所所所所所所");
//		System.out.println(map.get(b2.getBookId())+"===="+map.get(b2.getBookPrice())+"======"+map.get(b2.getBookName())+"===="+map.get(b2.getBookAuther()));
//		
//		p.print("比较加载器是否相同------");
//		p.print(map.get("获取的b2加载器："+b2.getClass().getClassLoader()));
//		p.println("新建Book类的类加载器："+Book.class.getClassLoader());
		
		
		List<List<?>> list=ic.query(new SqlFieldsQuery("select * from Book")).getAll();
		System.out.println("输出存入的book集合内容");
		for (List<?> bookList : list) {
			
			System.out.println("bookId= "+bookList.get(0)+"   bookPrice= " + bookList.get(1)+"   bookName= " + bookList.get(2)+"  bookAuther= "+bookList.get(3));
			
			p.println("bookId= "+bookList.get(0)+"   bookPrice= " + bookList.get(1)+"   bookName= " + bookList.get(2)+"  bookAuther= "+bookList.get(3));
		}
		
		System.out.println("输出完毕");
//		ic.close();
//		ignite.close();
		
	}

}
