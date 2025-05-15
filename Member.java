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
