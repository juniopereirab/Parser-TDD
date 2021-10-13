import java.io.File;

public class AnalysisFile {
    private File inputTimeAnalysis;

    public void openTimeAnalysis(String path) {
        inputTimeAnalysis = new File(path);
    }

    public File getTimeAnalysis() {
        return inputTimeAnalysis;
    }
}
