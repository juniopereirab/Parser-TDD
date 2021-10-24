import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ParserTest {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    private Parser p;

    @Before
    public void initialize() { p = new Parser(); }

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
        p.setOrientation('v');
        assertEquals('v', p.getOrientation());

        p.setOrientation('h');
        assertEquals('h', p.getOrientation());
    }

    @Test
    public void parseDataHorizontally() {
        AnalysisFile entryFile = new AnalysisFile();
        entryFile.openTimeAnalysis("assets/reducedExample1.out");
        entryFile.getDataFromFile();

        p.setContent(entryFile.getContent());
        p.setDelimiter(";");
        p.setOrientation('h');
        String res = "1;345;544;465;767\n2;703;812;800";
        assertEquals(res, p.getParsedData());

        AnalysisFile entryFile2 = new AnalysisFile();
        entryFile2.openTimeAnalysis("assets/reducedExample2.out");
        entryFile2.getDataFromFile();

        Parser p2 = new Parser();
        p2.setContent(entryFile2.getContent());
        p2.setDelimiter(";");
        p2.setOrientation('h');
        String res2 = "1;244;326;425;577\n2;503;734;799";
        assertEquals(res2, p2.getParsedData());
    }

    @Test
    public void parseDataVertically() {
        AnalysisFile entryFile = new AnalysisFile();
        entryFile.openTimeAnalysis("assets/reducedExample1.out");
        entryFile.getDataFromFile();

        p.setContent(entryFile.getContent());
        p.setDelimiter(";");
        p.setOrientation('v');
        String res = "1;2\n345;703\n544;812\n465;800\n767;";
        assertEquals(res, p.getParsedData());

        AnalysisFile entryFile2 = new AnalysisFile();
        entryFile2.openTimeAnalysis("assets/reducedExample2.out");
        entryFile2.getDataFromFile();

        Parser p2 = new Parser();
        p2.setContent(entryFile2.getContent());
        p2.setDelimiter(";");
        p2.setOrientation('v');
        String res2 = "1;2\n244;503\n326;734\n425;799\n577;";

        assertEquals(res2, p2.getParsedData());
    }
}