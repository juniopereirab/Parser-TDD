import java.io.File;

public class AnalysisFile {
    private File inputTimeAnalysis;
    private File inputMemoryAnalysis;

    public void openTimeAnalysis(String path) {
        inputTimeAnalysis = new File(path);
    }

    public File getTimeAnalysis() {
        return inputTimeAnalysis;
    }

    public void openMemoryAnalysis(String path) {
        inputMemoryAnalysis = new File(path);
    }

    public File getMemoryAnalysis() {
        return inputMemoryAnalysis;
    }
}
