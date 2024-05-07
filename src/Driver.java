// ----------------------------------------
// Assignment 0
// Question Part 2 -- Driver
// Written by: Lesley Ventura 40281652
// ----------------------------------------
/*	Purpose:
 *	This program allows user to modify and view the books in the bookstore.
 * 	There are 5 options available: 
 * 		- adding books
 * 		- updating the information of the books in bookshelf
 * 		- viewing all books by a specific author
 * 		- viewing all books under a certain price
 * 		- exiting the program.
 */
import java.util.Scanner;
public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int option;
		boolean valid = true;
		int totalAttempts = 0;
		
		// password of the owner of the bookstore
		final String PASSWORD = "249books";
		
		System.out.println("Welcome to Your Bookstore!\n");
		
		System.out.print("Please enter the maximum number of books your bookstore can contain: ");
		int maxBooks = input.nextInt();
		
		// creates array "inventory" with the size determined by user input
		Book[] inventory = new Book[maxBooks];
		
		/* will keep displaying Main Menu until user selects option 5 
		 * or user reaches 12 total failed attempts in option 1 */
		while (valid) {
			
			// use of static method to display main menu
			displayMainMenu();
			
			option = input.nextInt();
			input.nextLine(); // consume newline character
			
			// switch for operation of main menu options
			switch (option) {
			
			// adding new books to bookshelf
			case 1:
				int attempts = 0;
				
				// allows user to attempt password 3 times before main menu is re-displayed
				while (attempts < 3) {
					System.out.print("\nEnter your password (249books): ");
					String entered = input.nextLine();
					
					if (entered.equals(PASSWORD)) {
						addBooks(input, inventory);
						break;	// will break out of loop after adding books
						
					} else 		// wrong password
						attempts++;		
				}
				// once 3 attempts are reached, it results in a total attempt
				totalAttempts++;
				
				// when 4 total attempts are reached, program terminates
				if (totalAttempts == 4) {
					System.out.println("\nProgram detected suspicious activities and will terminate immediately!");
					valid = false; // boolean valid becomes false and ends program
				}
			
				break; 

			// update existing books in bookshelf
			case 2:
				int tries = 0;
				
				while (tries < 3 ) {
					System.out.print("\nEnter your password: ");
					String entered = input.next();
					
					if (entered.equals(PASSWORD)) {
						updateBook(input, inventory);
						break;
					} else 
						tries++;
				}
				break;
				
			// view all books under same author
			case 3:
				System.out.print("\nEnter an authors name: ");
				String name = input.nextLine();
				
				boolean sameAuthor = false;

				// for-loop goes through inventory array for books that share the same author
				for (int i = 0; i < inventory.length; i++) {
					Book book = inventory[i];
					if (book != null && book.getAuthor().equalsIgnoreCase(name)) {

						System.out.print("\nBook #" +(i+1)+ "\n"+book.toString());
						sameAuthor = true;
					}
				}
				
				if (!sameAuthor) {
					System.out.println("No books found for the author: " + name +".");
				}
				break;
				
			// view books under same price value
			case 4:
				System.out.print("Enter a price value: $");
				double value = input.nextDouble();
				
				boolean samePrice = false;
				
				// for-loop goes through inventory array for books that share the same price value
				for (int i = 0; i < inventory.length; i++) {
					Book book = inventory[i];

					if (book != null && book.getPrice() == value) {
						System.out.print("\nBook #" +(i+1)+ "\n"+book.toString());
						samePrice = true;
					}
				}
				
				if (!samePrice) {
					System.out.println("No books found for the author: " + value +".");
				}
				break;
			
			// exit program
			case 5:
				System.out.println("\nThank you for using this program.");
				System.exit(0);
				
			// user enters invalid choice (anything other than 1-5)
			default:
				System.out.println("\nNot a valid choice. Please Try again.");
			}
		}
	}
	
	// Display main menu
	public static void displayMainMenu() {
		System.out.print("\nWhat do you want to do?\n"
				+ "1. Enter new books (password required)\n"
				+ "2. Change information of a book (password required)\n"
				+ "3. Display all books by a specific author\n"
				+ "4. Display all books under a certain a price.\n"
				+ "5. Quit\n"
				+ "Please enter your choice > ");
	}
	
	// method checks if there is still available space in bookshelf (inventory[]) for user to add more books
	public static int checkInventory(Book[] inventory) {
		int counter = 0;
		for (int n = 0; n < inventory.length; n++) {
			if (inventory[n] != null)
				counter++;
		}
		return counter;
	}

	// method adds books
	public static void addBooks(Scanner input, Book[] inventory) {
		System.out.print("\nHow many books do you want to add? ");
		int numAddedBooks = input.nextInt();

		/* if desired number of books to add + current number of books in inventory are less than the length
		 * of the array, the for loop allow user to enter information for books */
		if ((checkInventory(inventory) + numAddedBooks) <= inventory.length) {
			
			for (int j = 0; j < numAddedBooks; j++) {

				// user enters information of new book
				System.out.print("\nAuthor: ");
				String author = input.next();
				
				System.out.print("Title: ");
				String title = input.next();
				
				System.out.print("ISBN: ");
				long ISBN = input.nextLong();
				
				System.out.print("Price: $");
				double price = input.nextDouble();
				
				// creates a new Book object using user input
				Book book = new Book(author, title, ISBN, price);
				
				// for-loop adds book to empty places in array
				for (int k = 0; k < inventory.length; k++) {
					if (inventory[k] == null) {
						inventory[k] = book;
						break;
					}
				}
			}
			
		} else {	// if there is no more sufficient space, error message
			int remaining = inventory.length - checkInventory(inventory);
			System.out.println("\nSpace is insufficient. Maximum remainding places: "+ remaining);
		}
	}
	
	// method updates existing books
	public static void updateBook(Scanner input, Book[] inventory) {
		System.out.print("\nWhich book would you like to update? ");
		int updateBook = input.nextInt();
		
		// displays information of book
		System.out.println(inventory[updateBook-1].toString());
		
		int choice;
		
		// do-while loop keeps prompting user for additional changes until option 5 is selected
		do {
			System.out.print("What information would you like to change?\n"
					+ "1. author\n"
					+ "2. title\n"
					+ "3. ISBN\n"
					+ "4. price\n"
					+ "5. Quit\n"
					+ "Enter your choice >");
				
			choice = input.nextInt();
			input.nextLine(); // consume newline character
			
			// switch-case takes user input and updates specific information
			switch(choice) {
			case 1:
				System.out.print("\nEnter a new author: ");
				String newAuthor = input.next();
				
				inventory[updateBook-1].setAuthor(newAuthor);				
				break;
				
			case 2:
				System.out.print("\nEnter a new title: ");
				String newTitle = input.next();
				
				inventory[updateBook-1].setTitle(newTitle);				
				break;
				
			case 3:
				System.out.print("\nEnter a new ISBN: ");
				long newISBN = input.nextLong();
				
				inventory[updateBook-1].setISBN(newISBN);				
				break;
				
			case 4:
				System.out.print("\nEnter a new price: ");
				double newPrice = input.nextDouble();
				
				inventory[updateBook-1].setPrice(newPrice);
				break;
			
			case 5:
				break;
				
			default:
				System.out.println("\nInvalid choice. Please Try Again.");
			}
			
			// after every update, the updated information of the book is displayed
			if (choice >= 1 && choice <= 4) {
				System.out.println("Book #" + updateBook + "\n" +inventory[updateBook-1].toString());
				
			}
		} while (choice != 5);
	}
}
