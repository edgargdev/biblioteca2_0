/**
 * Created by egonzale on 3/7/17.
 */
public class ReturnBookOption implements Option {
    private final String name;
    private final Biblioteca biblioteca;

    public ReturnBookOption(String name, Biblioteca biblioteca) {
        this.name = name;
        this.biblioteca = biblioteca;
    }

    public void execute() {
        biblioteca.returnBook();
    }

    public String getName() {
        return name;
    }
}
