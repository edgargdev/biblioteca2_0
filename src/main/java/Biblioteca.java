import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by egonzale on 3/1/17.
 */
public class Biblioteca {
    private PrintStream out;
    private ArrayList<Book> books;
    private final BufferedReader bufferedReader;
    private final List<Book> checkedOutBooks;

    public Biblioteca(PrintStream out, BufferedReader bufferedReader, ArrayList<Book> books, List<Book> checkedOutBooks) {
        this.out = out;
        this.books = books;
        this.bufferedReader = bufferedReader;
        this.checkedOutBooks = checkedOutBooks;
    }

    public void listBooks() {
        out.println("Title | Author | Year");
        for(Book book : books) {
            book.printDetails();
        }
    }


    public void checkOutBook() {
        out.println("Select book to checkout");
        String input = readLine();
        try {
            int choice = parseInt(input);
            Book checkedOutBook = books.remove(choice - 1);
            checkedOutBooks.add(checkedOutBook);

        } catch (Exception e) {
            out.println("That book is not available.");
        }
    }

    public void returnBook() {
        int choice = parseInt(readLine());
        Book returnedBook = checkedOutBooks.remove(choice - 1);
        books.add(returnedBook);
    }

    private String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
