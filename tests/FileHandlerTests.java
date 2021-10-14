import org.junit.Test;

import static org.junit.Assert.*;

public class FileHandlerTests {

    @Test
    public void receiveDelimiterChar() {
        char delimiter = ';';
        FileHandler handler = new FileHandler();
        handler.setDelimiter(delimiter);
        assertEquals(';', handler.getDelimiter());
    }
}
