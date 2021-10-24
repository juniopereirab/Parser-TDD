import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ParserTest {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    @Test
    public void receiveDataOutOrientation() {
        Parser p = new Parser(timeAnalysisPath, 'v');
        assertEquals('v', p.getOrientation());

        Parser p1 = new Parser(timeAnalysisPath, 'h');
        assertEquals('h', p1.getOrientation());
    }

    @Test
    public void parseDataHorizontally() {
        FileHandler handler = new FileHandler();
        handler.setDelimiter(";");
        Parser p = new Parser("assets/reducedExample1.out", 'h');
        p.setHandler(handler);
        String res = "1;345;544;465;767\n2;703;812;800";
        assertEquals(true, p.getDataFromFile());
        assertEquals(res, p.getParsedData());

        Parser p2 = new Parser("assets/reducedExample2.out", 'h');
        p2.setHandler(handler);
        String res2 = "1;244;326;425;577\n2;503;734;799";
        assertEquals(true, p2.getDataFromFile());
        assertEquals(res2, p2.getParsedData());
    }

    @Test
    public void parseDataVertically() {
        FileHandler handler = new FileHandler();
        handler.setDelimiter(";");
        Parser p = new Parser("assets/reducedExample1.out", 'v');
        p.setHandler(handler);
        String res = "1;2\n345;703\n544;812\n465;800\n767;";
        assertEquals(true, p.getDataFromFile());
        assertEquals(res, p.getParsedData());

        Parser p2 = new Parser("assets/reducedExample2.out", 'v');
        p2.setHandler(handler);
        String res2 = "1;2\n244;503\n326;734\n425;799\n577;";

        assertEquals(true, p2.getDataFromFile());
        assertEquals(res2, p2.getParsedData());
    }

    @Test
    public void saveParsedDataCorrectly() {
        FileHandler handler = new FileHandler();
        handler.setDelimiter(";");
        Parser p = new Parser(timeAnalysisPath, 'h');
        p.setHandler(handler);
        String parsedData = new String("");

        assertEquals(true, p.getDataFromFile());

        parsedData = p.getParsedData();
        assertEquals(true, p.saveParsedData(parsedData, "assets/final.out"));
    }

    @Test
    public void saveParsedDataIncorrectly() {
        FileHandler handler = new FileHandler();
        handler.setDelimiter(";");
        Parser p = new Parser("assets/analysisTime.out", 'h');
        p.setHandler(handler);
        String parsedData = new String("");

        assertEquals(true, p.getDataFromFile());

        parsedData = p.getParsedData();
        assertEquals(false, p.saveParsedData(parsedData, "assets/"));
    }
}