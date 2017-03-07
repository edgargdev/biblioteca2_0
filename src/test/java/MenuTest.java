import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

/**
 * Created by alexandraquintano on 3/2/17.
 */
public class MenuTest {
    private PrintStream printStream;
    private Menu menu;
    private BufferedReader bufferedReader;
    private Map<String, Option> choiceToOption;

    @Before
    public void setUp(){
        bufferedReader = mock(BufferedReader.class);
        printStream = mock(PrintStream.class);
        choiceToOption = new TreeMap<String, Option>();
        menu = new Menu(printStream, bufferedReader, choiceToOption);
    }

    @Test
    public void shouldPrintQuitOptionWhenChoosingOptions() throws IOException {
        noOptionChosen();
        menu.chooseOption();
        verify(printStream).println(contains("Quit"));
    }

    @Test
    public void shouldPromptTheUserForInput() throws IOException {
        noOptionChosen();
        menu.chooseOption();

        verify(printStream).println(contains("Enter option"));
    }

    @Test
    public void shouldNotifyCustomerWhenMenuSelectionIsInvalid() throws IOException {
        invalidOption();
        menu.chooseOption();
        verify(printStream).println("Select a valid option!");
    }

    @Test
    public void shouldTakeInputAfterInvalidMenuSelection() throws IOException {
        invalidOption();
        menu.chooseOption();
        verify(bufferedReader, times(2)).readLine();
    }

    @Test
    public void shouldAllowQuitOptionToBeValid() throws IOException {
        noOptionChosen();

        menu.chooseOption();

        assert(true);
    }

    @Test
    public void shouldContinueToChooseOptionsUntilUserQuits() throws IOException {
        multipleValidOptions();
        Option option = mock(Option.class);
        choiceToOption.put("1", option);


        menu.chooseOption();

        verify(option, atLeast(1)).execute();
    }


    private void noOptionChosen() throws IOException {
        when(bufferedReader.readLine()).thenReturn("q");
    }

    private void listBooksOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1", "q");
    }

    private void invalidOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("INVALID", "q");
    }

    private void multipleValidOptions() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1", "1", "q");
    }

}