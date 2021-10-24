import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ParserTest {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    @Test
    public void receiveDataOutOrientation() {
        Parser p = new Parser(timeAnalysisPath, 'v', ';');
        assertEquals('v', p.getOrientation());
        Parser p1 = new Parser(timeAnalysisPath, 'h', '/');
        assertEquals('h', p1.getOrientation());
    }

    @Test
    public void parseDataHorizontally() {
        Parser p = new Parser("assets/reducedExample1.out", 'h', ';');
        String res = "1;345;544;465;767\n2;703;812;800";

        assertEquals(true, p.getDataFromFile());
        assertEquals(res, p.getParsedData());

        Parser p2 = new Parser("assets/reducedExample2.out", 'h', ';');
        String res2 = "1;244;326;425;577\n2;503;734;799";

        assertEquals(true, p2.getDataFromFile());
        assertEquals(res2, p2.getParsedData());
    }

    @Test
    public void parseDataVertically() {
        Parser p = new Parser("assets/reducedExample1.out", 'v', ';');
        String res = "1;2\n345;703\n544;812\n465;800\n767;";

        assertEquals(true, p.getDataFromFile());
        assertEquals(res, p.getParsedData());

        Parser p2 = new Parser("assets/reducedExample2.out", 'v', ';');
        String res2 = "1;2\n244;503\n326;734\n425;799\n577;";

        assertEquals(true, p2.getDataFromFile());
        assertEquals(res2, p2.getParsedData());
    }

    @Test
    public void saveParsedDataCorrectly() {
        Parser p = new Parser(timeAnalysisPath, 'h', ';');
        String parsedData = new String("");

        assertEquals(true, p.getDataFromFile());

        parsedData = p.getParsedData();
        assertEquals(true, p.saveParsedData(parsedData, "assets/final.out"));
    }

    @Test
    public void saveParsedDataIncorrectly() {
        Parser p = new Parser("assets/analysisTime.out", 'h', ';');
        String parsedData = new String("");

        assertEquals(true, p.getDataFromFile());

        parsedData = p.getParsedData();
        assertEquals(false, p.saveParsedData(parsedData, "assets/"));
    }
}