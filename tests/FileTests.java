import org.junit.Test;

import java.io.File;

public class FileTests {

    @Test
    public void openAnalysisTime () {
        AnalysisFile file = new AnalysisFile();
        File response = file.open("analysisTime.out");
        assertTrue(response.exists());
    }
}
