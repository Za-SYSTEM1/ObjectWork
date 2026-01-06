package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;

    private Loan loan;

    public Book(int id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }

    public Book(int i, String title2, String author2, String publicationYear2, String publisher2) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }

    public String getPublisher(){
        return publisher;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
	
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() {
         return "Book #" + id +
                   "\nTitle: " + title +
                   "\nAuthor: " + author +
                   "\nPublication Year: " + publicationYear +
                   "\nPublisher: " + publisher +
               "\nStatus: " + (isOnLoan() ? "On Loan" : "Available");
        // TODO: implementation here
    }
    
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    public String getStatus() {
    	   if (isOnLoan()) {
    	        return "On loan (due " + loan.getDueDate() + ")";
    	    } else {
    	        return "Available";
    	    }
        // TODO: implementation here
    }

    public LocalDate getDueDate() {
        if (loan == null) {
            return null;
        }
        return loan.getDueDate();

    }
    
    public void setDueDate(LocalDate dueDate) throws LibraryException {
        if (loan == null) {
            throw new LibraryException("Book is not currently on loan.");
        }
        if (dueDate.isBefore(LocalDate.now())) {
            throw new LibraryException("Due date cannot be in the past.");
        }
        loan.setDueDate(dueDate);
    }


    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
}
