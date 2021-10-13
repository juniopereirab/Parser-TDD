import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileTests {

    @Test
    public void openAnalysisTime () {
        AnalysisFile file = new AnalysisFile();
        File response = file.open("../assets/analysisTime.out");
        assertTrue(response.exists());
    }
}
