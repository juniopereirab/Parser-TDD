import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileTests {

    @Test
    public void openAnalysisTime () {
        AnalysisFile file = new AnalysisFile();
        file.openTimeAnalysis("assets/analysisTime.out");
        assertTrue(file.getTimeAnalysis().exists());
    }

    @Test
    public void openAnalysisMemory () {
        AnalysisFile file = new AnalysisFile();
        file.openMemoryAnalysis("assets/analysisMemory.out");
        assertTrue(file.getMemoryAnalysis().exists());
    }

    @Test
    public void configureAnalysisFiles() {
        AnalysisFile file = new AnalysisFile();
        file.config();
        assertTrue(file.getMemoryAnalysis().exists());
        assertTrue(file.getTimeAnalysis().exists());
    }
}
