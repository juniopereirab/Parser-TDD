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

    @Test
    public void parseDataHorizontally() {
        Parser p = new Parser("assets/reducedExample1.out", 'h', ';');
        String res = "1;345;544;465;767\n2;703;812;800";

        assertEquals(true, p.getDataFromFile("assets/reducedExample1.out"));
        assertEquals(res, p.getParsedData());

        Parser p2 = new Parser("assets/reducedExample1.out", 'h', ';');
        String res2 = "1;345;544;465;767\n2;703;812;800";

        assertEquals(true, p2.getDataFromFile("assets/reducedExample1.out"));
        assertEquals(res2, p2.getParsedData());
    }
}