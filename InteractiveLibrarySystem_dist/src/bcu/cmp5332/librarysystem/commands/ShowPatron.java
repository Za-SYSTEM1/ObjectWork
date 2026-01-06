package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

public class ShowPatron implements Command {

    private final int patronId;

    public ShowPatron(int patronId) {
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(patronId);

        System.out.println("Patron #" + patron.getId());
        System.out.println("Name: " + patron.getName());
        System.out.println("Phone: " + patron.getPhone());

        if (patron.getBooks().isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("Borrowed books:");
            for (Book book : patron.getBooks()) {
                System.out.println(" - " + book.getDetailsShort());
            }
        }
    }
}
