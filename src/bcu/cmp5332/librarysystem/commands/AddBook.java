package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.List;

public class AddBook implements  Command {

    private final String title;
    private final String author;
    private final String publicationYear;
    private final String publisher;

    public AddBook(String title, String author, String publicationYear, String publisher) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
        int maxId = 0;
        for (Book b : books) {
            if (b.getId() > maxId) {
                maxId = b.getId();
            }
        }

        Book book = new Book(maxId + 1, title, author, publicationYear, publisher);

        // Add to library
        library.addBook(book);

        System.out.println("Book #" + book.getId() + " added: " + title + " by " + author);
    }
}
 