package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import  bcu.cmp5332.librarysystem.model.Book;
import  bcu.cmp5332.librarysystem.model.Library;
import  bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class RenewLoan implements Command {
	
	private final int patronId;
	private final int bookId;
	
 public RenewLoan(int patronId, int bookId) {
	 
	 this.patronId = patronId;
	 this.bookId = bookId;
	 
}
 
 @Override
 public void execute(Library library, LocalDate currentDate) throws LibraryException{
	 Patron patron = library.getPatronByID(patronId);
	 Book book = library.getBookByID(bookId);
	 
	 if (!book.isOnLoan() || book.getLoan() == null) {
		 throw new LibraryException("The book currently isnt on Loan.");
	 }
	 
	 LocalDate base = book.getLoan().getDueDate();
	 LocalDate newDue = base.plusDays(library.getLoanPeriod());
	 
	 patron.renewBook(book, newDue);
	 System.out.println("Renewed: " + book.getDetailsShort() + " (new due" + newDue + ")");
 }
}
