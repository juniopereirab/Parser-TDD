import org.junit.Test;

import static org.junit.Assert.*;

public class FileHandlerTests {

    @Test
    public void receiveDelimiterChar() {
        String delimiter = ";";
        FileHandler handler = new FileHandler();
        handler.setDelimiter(delimiter);
        assertEquals(';', handler.getDelimiter());
    }

    @Test(expected = DelimitadorInvalidoException.class)
    public void receiveDelimiterString() {
        String delimiter = "test";
        FileHandler handler = new FileHandler();
        handler.setDelimiter(delimiter);
    }
}
