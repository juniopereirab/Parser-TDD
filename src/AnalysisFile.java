import java.io.File;

public class AnalysisFile {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    private static final String memoryAnalysisPath = "assets/analysisMemory.out";

    private File inputTimeAnalysis;
    private File inputMemoryAnalysis;

    public void config() {
        this.openMemoryAnalysis(memoryAnalysisPath);
        this.openTimeAnalysis(timeAnalysisPath);
    }

    protected void openTimeAnalysis(String path) {
        inputTimeAnalysis = new File(path);
    }

    public File getTimeAnalysis() {
        return inputTimeAnalysis;
    }

    protected void openMemoryAnalysis(String path) {
        inputMemoryAnalysis = new File(path);
    }

    public File getMemoryAnalysis() {
        return inputMemoryAnalysis;
    }


}
