import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

/**
 * Created by alexandraquintano on 3/2/17.
 */
public class ApplicationTest {

    private PrintStream out;
    private Biblioteca biblioteca;
    private Menu menu;
    private BufferedReader in;
    private Application app;

    @Before
    public void setUp() throws IOException {
        out = mock(PrintStream.class);
        biblioteca = mock(Biblioteca.class);
        menu = mock(Menu.class);
        in = mock(BufferedReader.class);
        when(in.readLine()).thenReturn("2");
        app = new Application(in, out, biblioteca, menu);
    }
    @Test
    public void shouldPrintWelcomeMessageWhenApplicationStarts() {
        app.start();
        verify(out).println("Welcome");
    }
    @Test
    public void shouldPrintMenuOptionsWhenStarting() {
        app.start();
        verify(menu).displayMenu();
    }

    @Test
    public void shouldCallListBooksWhenSelectingListOptionInMenu() throws IOException {
        when(in.readLine()).thenReturn("1");
        app.start();
        verify(biblioteca).listBooks();
    }

    @Test
    public void shouldNotifyCustomerWhenMenuSelectionIsInvalid() throws IOException {
        when(in.readLine()).thenReturn("8", "1");
        app.start();
        verify(out).println("Select a valid option!");
    }

    @Test
    public void shouldTakeInputAfterInvalidMenuSelection() throws IOException {
        when(in.readLine()).thenReturn("selection", "1");
        app.start();
        verify(in, times(2)).readLine();
    }


    @Test
    public void shouldContinueMenuSelectionUntilQuitCommand() throws IOException {
        when(in.readLine()).thenReturn("1", "1", "test", "2");
        app.start();
        verify(in, times(4)).readLine();
    }

}