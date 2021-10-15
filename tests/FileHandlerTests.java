import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FileHandlerTests {
    private static final String outputPath = "assets/final.out";

    @Parameters
    public static Iterable getParameters() {
        Object[][] data = new Object[][] {
                {";", ';'},
                {"\r", '\r'},
                {"\n", '\n'},
                {"\t", '\t'}
        };
        return Arrays.asList(data);
    }

    private FileHandler handler;
    private String delimiter;
    private char expected;

    public FileHandlerTests(String delimiter, char expected) {
        this.delimiter = delimiter;
        this.expected = expected;
    }

    @Before
    public void initialize() {
        handler = new FileHandler();
    }

    @Test
    public void receiveDelimiterCharWithSuccess() {
        handler.setDelimiter(delimiter);
        assertEquals(expected, handler.getDelimiter());
    }

    @Test(expected = DelimitadorInvalidoException.class)
    public void receiveDelimiterStringWithFailure() {
        String delimiter = "test";
        handler.setDelimiter(delimiter);
    }

    @Test
    public void createFileWriter() {
        handler.setWriter(outputPath);
        assertNotNull(handler.getWriter());
    }

    @Test
    public void writeInFile() {
        handler.setWriter(outputPath);
        handler.writeFile();

        try {
            FileReader outputFile = new FileReader(outputPath);
            BufferedReader buffReader = new BufferedReader(outputFile);
            String text = buffReader.readLine();
            assertEquals("teste", text);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
