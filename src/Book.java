// ----------------------------------------
// Assignment 0
// Question Part 1 - Book Class
// Written by: Lesley Ventura 40281652
// ----------------------------------------
/*	Purpose:
 * 	This Book class allows manipulation of Book objects in driver. */

public class Book {
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private int numBooks;
	
	// default constructor
	public Book() {
		
	}
	
	// custom constructor with 4 parameters: author, title , ISBN, and price
	public Book(String author, String title, long ISBN, double price) {
		this.author = author;
		this.title = title;
		this.ISBN = ISBN;
		this.price = price;
	}
	
	// getter and setter methods for all attributes
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public long getISBN() {
		return ISBN;
	}
	
	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	// method that will return number of created Book objects
	public int findNumberOfCreatedBooks() {
		if (numBooks == 0) 
			return 0;
		 else 
			return numBooks;
	}
	
	// equals() method that will evaluate the values between two Book objects to compare ISBN and prices
	public boolean equals(Book other) {
		return this.ISBN == other.ISBN && this.price == other.price;
	}
	
	// overriding toString() to customize how the information of a Book object is displayed
	public String toString() {
		return "Author: " + author + "\n"+
					"Title: " + title + "\n" +
					"ISBN: " + ISBN + "\n" +
					"Price: $" + price + "\n";
	}
}
