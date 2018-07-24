package com.bee.sample.ch1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bee.sample.ch1.vo.Book1;


@Mapper
public interface BookMapper {
	
	@Select("select * from book")
	List<Book1> queryBooks();
	
	@Insert("insert into book (bookid,bookname,bookauther,bookprice) value(#{b.bookId},#{b.bookName},#{b.bookAuther},#{b.bookPrice})")
	int addBook(@Param("b")Book1 b);

}
