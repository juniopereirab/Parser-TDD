import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AnalysisFile {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    private static final String memoryAnalysisPath = "assets/analysisMemory.out";
    
    private FileReader timeAnalysis;
    private FileReader memoryAnalysis;

    public void config(){
        try {
            this.openMemoryAnalysis(memoryAnalysisPath);
            this.openTimeAnalysis(timeAnalysisPath);
        }
        catch(ArquivoNaoEncontradoException error){
            error.printStackTrace();
        }
    }

    protected void openTimeAnalysis(String path) throws ArquivoNaoEncontradoException{
        try {
            timeAnalysis = new FileReader(path);
        }
        catch(FileNotFoundException e){
            throw new ArquivoNaoEncontradoException(path);
        }

    }

    protected void openMemoryAnalysis(String path) throws ArquivoNaoEncontradoException{
        try{
            memoryAnalysis = new FileReader(path);
        } catch (FileNotFoundException e){
            throw new ArquivoNaoEncontradoException(path);
        }
    }

    public FileReader getTimeAnalysis() {
        return timeAnalysis;
    }
    public FileReader getMemoryAnalysis() {
        return memoryAnalysis;
    }

}
