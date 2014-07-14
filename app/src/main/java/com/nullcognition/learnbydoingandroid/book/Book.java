package com.nullcognition.learnbydoingandroid.book;

/**
 * Created by ersin on 04/07/14.
 */
public class Book{

	public Book(String inTitle, String inAuthor){
		title = inTitle;
		author = inAuthor;
	}

	private String title;
	private String author;

	public String getTitle(){
		return title;
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String inAuthor){
		author = inAuthor;
	}

	public void setTitle(String inTitle){
		title = inTitle;
	}


}
