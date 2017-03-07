import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by egonzale on 3/1/17.
 */
public class BibliotecaTest {

    private PrintStream printStream;
    private ArrayList<Book> books;
    private BufferedReader bufferedReader;
    private Biblioteca biblioteca;
    private Book book;
    private List<Book> checkedOutBooks;

    @Before
    public void setUp() {
        books = new ArrayList<Book>();
        book = mock(Book.class);
        books.add(book);
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        biblioteca = new Biblioteca(printStream, bufferedReader, books, checkedOutBooks);
    }


    @Test
    public void shouldPrintOutNamesOfBooks() {
        biblioteca.listBooks();

        verify(book).printDetails();
    }

    @Test
    public void shouldDeleteBookFromListWhenCheckingOutABook() throws IOException {
        Book checkoutBook = mock(Book.class);
        books.add(checkoutBook);
        selectSecondBookOption();

        biblioteca.checkOutBook();

        assertThat(books.contains(checkoutBook), is(false));
    }

    @Test
    public void shouldNotifyThatCheckoutBookWasUnsuccessful() throws IOException {
        selectInvalidBookOption();

        biblioteca.checkOutBook();

        verify(printStream).println("That book is not available.");
    }

    @Test
    public void shouldMoveBookToBeCheckedOutToListOfCheckedOutBooks() throws IOException {
        selectFirstBookOption();
        List<Book> checkedOutBooks = new ArrayList<Book>();
        Biblioteca biblioteca = new Biblioteca(printStream, bufferedReader, books, checkedOutBooks);

        biblioteca.checkOutBook();

        assertThat(checkedOutBooks.contains(book), is(true));
    }

    @Test
    public void shouldReturnACheckedOutBookWhenReturningThatBook() throws IOException {
        selectFirstBookOption();
        ArrayList<Book> books = new ArrayList<Book>();
        Book returnedBook = mock(Book.class);
        List<Book> checkedOutBooks = new ArrayList<Book>(Arrays.asList(returnedBook));
        Biblioteca biblioteca = new Biblioteca(printStream, bufferedReader, books, checkedOutBooks);

        biblioteca.returnBook();

        assertThat(books.contains(returnedBook), is(true));
    }

    @Test
    public void shouldReturnSecondCheckedOutBookToListOfBooks() throws IOException {
        selectSecondBookOption();
        Book returnedBook = mock(Book.class);
        Book secondReturnedBook = mock(Book.class);
        List<Book> checkedOutBooks = new ArrayList<Book>(Arrays.asList(returnedBook, secondReturnedBook));
        Biblioteca biblioteca = new Biblioteca(printStream, bufferedReader, books, checkedOutBooks);

        biblioteca.returnBook();

        assertThat(books.contains(secondReturnedBook), is(true));
    }

    private void selectFirstBookOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");
    }

    private void selectSecondBookOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");
    }

    private void selectInvalidBookOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("INVALID INPUT");
    }

}