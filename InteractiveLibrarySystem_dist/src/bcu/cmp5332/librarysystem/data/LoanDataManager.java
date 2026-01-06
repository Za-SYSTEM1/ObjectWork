package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;


import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.*;

import java.io.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {
    
    private final String RESOURCE = "./resources/data/loans.txt";
    private static final String SEPARATOR = "::";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        // TODO: implementation here
    	
    	File file = new File(RESOURCE);
    	if(!file.exists()) return;
    	
    	try(Scanner sc = new Scanner(file)) {
    		while (sc.hasNextLine()) {
    			String line = sc.nextLine().trim();
    			if(line.isEmpty())continue;
    			
    			String[] parts = line.split(SEPARATOR);
    			
    			int bookId = Integer.parseInt(parts[0]);
    			int patronId = Integer.parseInt(parts[1]);
    			LocalDate startDate = LocalDate.parse(parts[2]);
    			LocalDate dueDate = LocalDate.parse(parts[3]);
    			
    			Book book = library.getBookByID(bookId);
    			Patron patron = library.getPatronByID(patronId);
    			
    			Loan loan = new Loan(patron, book, startDate, dueDate);
    			book.setLoan(loan);
    			
    			patron.addBorrowedBook(book);
    			
    		}
    	}
    	
    	
    }

    @Override
    public void storeData(Library library) throws IOException {
        // TODO: implementation here
    	try(PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		for(Book b: library.getBooks()) {
    			if(b.isOnLoan() && b.getLoan() != null) {
    				Loan loan = b.getLoan();
    				out.println(b.getId() + SEPARATOR
    						+ loan.getPatron().getId() + SEPARATOR
    						+ loan.getStartDate() + SEPARATOR
    						+ loan.getDueDate() + SEPARATOR);
    			}
    		}
    	}
    }
    
}
 