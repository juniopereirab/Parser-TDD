import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void receiveDataOutOrientation() {
        Parser p = new Parser(null, 'v', ';');
        assertEquals('v', p.getOrientation());
        Parser p1 = new Parser(null, 'h', '/');
        assertEquals('h', p1.getOrientation());
    }
}