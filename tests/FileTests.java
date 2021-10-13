import org.junit.Test;

import static org.junit.Assert.*;

public class FileTests {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    private static final String memoryAnalysisPath = "assets/analysisMemory.out";

    @Test
    public void openAnalysisTime () {
        try {
            AnalysisFile file = new AnalysisFile();
            file.openTimeAnalysis(timeAnalysisPath);
            assertTrue(file.getTimeAnalysis().isFile());
        }
        catch(ArquivoNaoEncontradoException e){
            e.printStackTrace();
        }

    }

    @Test
    public void openAnalysisMemory () {
        try{
            AnalysisFile file = new AnalysisFile();
            file.openMemoryAnalysis(memoryAnalysisPath);
            assertTrue(file.getMemoryAnalysis().isFile());
        }
        catch(ArquivoNaoEncontradoException e){
            e.printStackTrace();
        }
    }

    @Test
    public void configureAnalysisFiles() {
        AnalysisFile file = new AnalysisFile();
        file.config();
        assertTrue(file.getMemoryAnalysis().isFile());
        assertTrue(file.getTimeAnalysis().isFile());
    }

    @Test(expected = ArquivoNaoEncontradoException.class)
    public void failToOpenFileIndividually() {
        AnalysisFile file = new AnalysisFile();
        file.openMemoryAnalysis("assets/");
        file.openTimeAnalysis("assets/");
    }
}
