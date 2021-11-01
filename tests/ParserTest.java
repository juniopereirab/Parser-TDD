import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ParserTest {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    private Parser p;

    @Before
    public void initialize() { p = new Parser(';', 'v', timeAnalysisPath); }

    @Test
    public void receiveDelimiterCharWithSuccess() {
        p.setDelimiter(";");
        assertEquals(';', p.getDelimiter());
        p.setDelimiter("\r");
        assertEquals('\r', p.getDelimiter());
        p.setDelimiter("\n");
        assertEquals('\n', p.getDelimiter());
        p.setDelimiter("\t");
        assertEquals('\t', p.getDelimiter());
    }

    @Test(expected = DelimitadorInvalidoException.class)
    public void receiveDelimiterStringWithFailure() {
        String delimiter = "test";
        p.setDelimiter(delimiter);
    }

    @Test
    public void receiveDataOutOrientation() {
        p.setOrientation("v");
        assertEquals('v', p.getOrientation());

        p.setOrientation("h");
        assertEquals('h', p.getOrientation());
    }

    @Test(expected = OrientacaoInvalidaException.class)
    public void receiveLongerOrientation() {
        p.setOrientation("teste");
        assertEquals("teste", p.getOrientation());
    }

    @Test
    public void parseDataHorizontally() {
        Parser p1 = new Parser(';', 'h', "assets/reducedExample1.out");
        String res = "1;345;544;465;767\n2;703;812;800";
        assertEquals(true, p1.getFileContent());
        assertEquals(res, p1.getParsedData());

        Parser p2 = new Parser(';', 'h', "assets/reducedExample2.out");
        String res2 = "1;244;326;425;577\n2;503;734;799";
        assertEquals(true, p2.getFileContent());
        assertEquals(res2, p2.getParsedData());
    }

    @Test
    public void parseDataVertically() {
        Parser p1 = new Parser(';', 'v', "assets/reducedExample1.out");
        String res = "1;2\n345;703\n544;812\n465;800\n767;";
        assertEquals(true, p1.getFileContent());
        assertEquals(res, p1.getParsedData());

        Parser p2 = new Parser(';', 'v', "assets/reducedExample2.out");
        String res2 = "1;2\n244;503\n326;734\n425;799\n577;";
        assertEquals(true, p2.getFileContent());
        assertEquals(res2, p2.getParsedData());
    }
}