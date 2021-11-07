import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FileHandlerTests {
    private static final String outputPath = "assets/final.out";

    private FileHandler handler;

    @Before
    public void initialize() {
        handler = new FileHandler();
    }

    @Test
    public void createFileWriter() {
        try{
            handler.setWriter(outputPath);
            assertNotNull(handler.getWriter());
        }
        catch(EscritaNaoPermitidaException e){
            e.printStackTrace();
        }
    }

    @Test
    public void writeInFile() {
        try {
            handler.setWriter(outputPath);
            handler.setResult("teste");
            handler.writeFile();
            FileReader outputFile = new FileReader(outputPath);
            BufferedReader buffReader = new BufferedReader(outputFile);
            String text = buffReader.readLine();
            buffReader.close();
            assertEquals("teste", text);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test(expected = EscritaNaoPermitidaException.class)
    public void notAllowedToWriteFile() {
            handler.setWriter("assets/");
            handler.setResult("teste");
            handler.writeFile();
    }
}
