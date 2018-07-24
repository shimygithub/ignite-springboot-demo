package com.bee.sample.ch1.vo;


public class Book1 {
	
	private String bookId;
	
	private double bookPrice;
	
	private String bookName;
	
	private String bookAuther;

	
	public Book1() {
		
	}
	
	public Book1(String id,double p,String bn,String ba) {
		this.bookId=id;
		this.bookPrice=p;
		this.bookName=bn;
		this.bookAuther=ba;
		
	}



	public String getBookId() {
		return bookId;
	}



	public void setBookId(String bookId) {
		this.bookId = bookId;
	}



	public double getBookPrice() {
		return bookPrice;
	}



	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}



	public String getBookName() {
		return bookName;
	}



	public void setBookName(String bookName) {
		this.bookName = bookName;
	}



	public String getBookAuther() {
		return bookAuther;
	}



	public void setBookAuther(String bookAuther) {
		this.bookAuther = bookAuther;
	}
	

}
