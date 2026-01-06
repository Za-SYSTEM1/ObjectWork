package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patron {

    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Book> books = new ArrayList<>();

    public Patron(int id, String name, String phone, String email) throws LibraryException {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
        if (book == null) {
            throw new LibraryException("Book cannot be null.");
        }
        if (book.isOnLoan()) {
            throw new LibraryException("Book is already on loan.");
        }
        if (dueDate.isBefore(LocalDate.now())) {
            throw new LibraryException("Due date cannot be in the past.");
        }

        Loan loan = new Loan(this, book, LocalDate.now(), dueDate);
        book.setLoan(loan);
        books.add(book);
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
        if (!books.contains(book)) {
            throw new LibraryException("This book is not borrowed by this patron.");
        }
        if (dueDate.isBefore(LocalDate.now())) {
            throw new LibraryException("Due date cannot be in the past.");
        }

        book.getLoan().setDueDate(dueDate);
    }

    public void returnBook(Book book) throws LibraryException {
        if (!books.contains(book)) {
            throw new LibraryException("This book is not borrowed by this patron.");
        }

        book.returnToLibrary();
        books.remove(book);
    }
}
