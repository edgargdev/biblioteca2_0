import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by egonzale on 3/6/17.
 */
public class ListBookOptionTest {

    @Test
    public void shouldCallLiwstBooksWhenExecuted() throws IOException {
        Biblioteca biblioteca = mock(Biblioteca.class);
        ListBookOption option  = new ListBookOption("", biblioteca);
        option.execute();
        verify(biblioteca).listBooks();
    }

}