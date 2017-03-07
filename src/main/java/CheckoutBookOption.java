/**
 * Created by egonzale on 3/6/17.
 */
public class CheckoutBookOption implements Option {
    private String name;
    private Biblioteca biblioteca;

    public CheckoutBookOption(String name, Biblioteca biblioteca) {
        this.name = name;
        this.biblioteca = biblioteca;
    }

    public void execute() {
        biblioteca.checkOutBook(1);
    }

    public String getName() {
        return name;
    }
}
