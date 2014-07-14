package com.nullcognition.learnbydoingandroid.book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ersin on 04/07/14.
 */
public class BookStore{

	public static List<Book> getBooks(){

		List<Book> books = new ArrayList<Book>();

		books.add(new Book("One", "Two"));
		books.add(new Book("three", "four"));
		books.add(new Book("seven", "six"));
		books.add(new Book("eight", "nine"));
		books.add(new Book("ten", "eleven"));
		books.add(new Book("twelve", "thirteen"));

		books.add(new Book("One", "Two"));
		books.add(new Book("three", "four"));
		books.add(new Book("seven", "six"));
		books.add(new Book("eight", "nine"));
		books.add(new Book("ten", "eleven"));
		books.add(new Book("twelve", "thirteen"));

		books.add(new Book("One", "Two"));
		books.add(new Book("three", "four"));
		books.add(new Book("seven", "six"));
		books.add(new Book("eight", "nine"));
		books.add(new Book("ten", "eleven"));
		books.add(new Book("twelve", "thirteen"));

		books.add(new Book("One", "Two"));
		books.add(new Book("three", "four"));
		books.add(new Book("seven", "six"));
		books.add(new Book("eight", "nine"));
		books.add(new Book("ten", "eleven"));
		books.add(new Book("twelve", "thirteen"));

		return books;
	}
}
