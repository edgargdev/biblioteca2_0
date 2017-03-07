import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by egonzale on 3/7/17.
 */
public class ReturnBookOptionTest {
    @Test
    public void shouldRunReturnBookMethodWhenExecuting() {
        Biblioteca biblioteca = mock(Biblioteca.class);
        ReturnBookOption option = new ReturnBookOption("", biblioteca);

        option.execute();

        verify(biblioteca).returnBook();
    }

}