import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by egonzale on 3/1/17.
 */
public class Main {
    public static void main(String[] args) {
        PrintStream printStream = System.out;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
        books.add(new Book("1984", "George Orwell", "1983", printStream));
        books.add(new Book("Green Eggs and Ham", "Dr. Suess", "1904", printStream));
        books.add(new Book("Sex at Dawn", "IDK", "1987", printStream));

        Biblioteca biblioteca = new Biblioteca(printStream, bufferedReader, books, checkedOutBooks);

        Map<String, Option> keyToOption = new TreeMap<String, Option>();
        keyToOption.put("1", new ListBookOption("List Books", biblioteca));
        keyToOption.put("2", new CheckoutBookOption("Checkout Book", biblioteca));
        keyToOption.put("3", new ReturnBookOption("Return Book", biblioteca));

        Menu menu = new Menu(printStream, bufferedReader, keyToOption);
        Application app = new Application(printStream, menu);

        app.start();
    }
}
