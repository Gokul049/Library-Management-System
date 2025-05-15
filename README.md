# Library-Management-System
• features two-way login for admin and users; admins can manage books and members, while users can
borrow and return books.
• applied OOPs principles for modular design.

# program
Main.java
----------------------------------------------------------------------------------------------------------
package library;

import java.util.*;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static List<Book> books = new ArrayList<>();
	static List<Member> members = new ArrayList<>();
	static Member loggedInMember = null;
	static final String adminUsername = "admin";
	static final String adminPassword = "admin123";

	public static void main(String[] args)
	{
		books.add(new Book("Java", "Gokul", "Programming"));
		books.add(new Book("The Laugh", "Vivek", "Comedy"));
		
		members.add(new Member("Gokul"));
		members.add(new Member("Logesh"));
		members.add(new Member("Aravind"));
		
		
		while(true)
		{
			System.out.print("Enter role(Admin/User): ");
			String role = scan.nextLine();
			
			if(role.equals("admin"))
			{
				loginAsAdmin();
			}
			else if(role.equals("user"))
			{
				loginAsUser();
			}
		}
		
		
	}

	private static void loginAsAdmin() {
		// TODO Auto-generated method stub
		System.out.print("Enter UserName: ");
		String username = scan.nextLine();
		System.out.print("Enter Password: ");
		String password = scan.nextLine();
		
		if(adminUsername.equals(username) && adminPassword.equals(password))
		{
			System.out.println("Logged in as Admin...");
			adminMenu();
		}
		else
		{
			System.out.println("Invalid Login Credentials...");
		}
		
	}
	
	private static void loginAsUser() {
		// TODO Auto-generated method stub
		System.out.print("Enter username: ");
        String username = scan.nextLine();

        loggedInMember = findOrCreateMember(username);
        if(loggedInMember!=null)
        {
        	System.out.println("Logged in as user.");
        	userMenu();
        }
        else
        {
        	System.out.println("Enter Valid User Or Create User...!!!");
        }
		
	}

	private static Member findOrCreateMember(String username) {
		for (Member member : members) {
	        //if member already exist, the return memeber
	            if (member.username.equals(username)) {
	                return member;
	            }
	        }
	        return null;
	}

	private static void adminMenu() {
		 while (true) {
	            System.out.println("\nLibrary Management System");
	            System.out.println("1. Add Book");
	            System.out.println("2. Update Book");
	            System.out.println("3. Remove Book");
	            System.out.println("4. Add Member");
	            System.out.println("5. Display All Books");
	            System.out.println("6. Display All Members");
	            System.out.println("7. Exit");

	            System.out.print("Enter your choice: ");
	            int choice = scan.nextInt();
	            scan.nextLine();
	            
	            switch (choice) {
                case 1: 
                	addBook(); 
                	break;
                case 2: 
                	updateBook(); 
                	break;
                case 3: 
                	removeBook(); 
                	break;
                case 4: 
                	addMember(); 
                	break;
                case 5: 
                	displayAllBooks();
                	break;
                case 6: 
                	displayAllMembers(); 
                	break;
                case 7: 
                	return;
                default: 
                	System.out.println("Invalid choice. Please try again.");
            }
		 }
		
	}
	
	private static void addBook() {
		System.out.print("Enter Book Title: ");
		String title = scan.nextLine();
		System.out.print("Enter Book Author: ");
		String author = scan.nextLine();
		System.out.print("Enter Book Genre: ");
		String genre = scan.nextLine();
		
		books.add(new Book(title, author, genre));
		System.out.println("Book Added Successfully!!!");
		
	}
	
	private static void updateBook() {
        System.out.print("Enter book title to update: ");
        String title = scan.nextLine();

        Book book = findBookByTitle(title);
        if (book != null) {
            System.out.print("Enter new author: ");
            book.author = scan.nextLine();
            System.out.print("Enter new genre: ");
            book.genre = scan.nextLine();
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

	private static Book findBookByTitle(String title) {
		for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
	}
	
	private static void removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = scan.nextLine();

        Book book = findBookByTitle(title);
        if (book != null) {
            books.remove(book);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
	
	private static void addMember() {
        System.out.print("Enter member username: ");
        String username = scan.nextLine();
        members.add(new Member(username));
        System.out.println("Member added successfully.");
    }
	
	private static void displayAllBooks() {
        if(books.isEmpty()){
            System.out.println("No books available");
        }else{
            System.out.println("\nList of Books:");
            for (Book book : books) {
                System.out.println("Title: " + book.title + ", Author: " + book.author + ", Genre: " + book.genre + ", Available: " + book.isavailable);
            }
        }
    }
	
	private static void displayAllMembers() {
        if(members.isEmpty()){
            System.out.println("No memebers exist");
        }else{
            System.out.println("\nList of Members:");
            for (Member member : members) {
                System.out.println("Username: " + member.username + ", Borrowed Books: " + member.borrowedBooks.size());
            }
        }
    }

	private static void userMenu() {
		while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Display All Members");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine();  // consume the newline character

            switch (choice) {
                case 1: borrowBook(); break;
                case 2: returnBook(); break;
                case 3: displayAllBooks(); break;
                case 4: displayAllMembers(); break;
                case 5: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
		
	}
	
	private static void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = scan.nextLine();

        Book book = findBookByTitle(title);
        //Explain this with an example senario
    //This checks if the loggedInMember successfully borrows the book.
        if (book != null && loggedInMember.borrowBook(book)) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is either unavailable or borrow limit reached.");
        }
    }
	
	 private static void returnBook() {
	        System.out.print("Enter book title to return: ");
	        String title = scan.nextLine();

	        Book book = findBookByTitle(title);
	        if (book != null && !book.isavailable) {
	 //This method is in the members class, remove it from borrowedBooks list
	 //return true or false accordingly
	            loggedInMember.returnBook(book);
	            System.out.println("Book returned successfully.");
	        } else {
	            System.out.println("Book not found or it wasn't borrowed.");
	        }
	    }

	
}
----------------------------------------------------------------------------------------------------------
Book.java
----------------------------------------------------------------------------------------------------------
package library;

import java.util.*;

public class Book {
	String title;
	String author;
	String genre;
	boolean isavailable;

	Book(String title, String author, String genre)
	{
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.isavailable = true;
	}
	
----------------------------------------------------------------------------------------------------------
Member.java
----------------------------------------------------------------------------------------------------------
package library;

import java.util.*;

public class Member {
	
	String username;
	List<Book> borrowedBooks ;
	
	Member(String username)
	{
		this.username = username;
		this.borrowedBooks = new ArrayList<>();
	}

	public boolean borrowBook(Book book) {
		if (borrowedBooks.size() < 5 && book.isavailable) {
            borrowedBooks.add(book);
            book.isavailable = false;  // Mark the book as borrowed
            return true;
        }
        return false;
	}

	public void returnBook(Book book) {
		// TODO Auto-generated method stub
		borrowedBooks.remove(book);
        book.isavailable = true;  // Mark the book as available
	}
	
	
	
}
# output
![Screenshot 2025-05-15 112828](https://github.com/user-attachments/assets/9e71bcdb-5222-4661-9619-270e1ae09e2a)

