import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalysisFile {
    private static final String timeAnalysisPath = "assets/analysisTime.out";
    private static final String memoryAnalysisPath = "assets/analysisMemory.out";
    
    private FileReader timeAnalysis;
    private FileReader memoryAnalysis;

    private List<String> content = new ArrayList<>();

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
    public List<String> getContent() { return content; }

    public void getDataFromFile() {
        try {
            BufferedReader buffReader = new BufferedReader(timeAnalysis);

            this.checkAndAddContent(buffReader);

            buffReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void checkAndAddContent(BufferedReader buffReader) throws Exception {
        try{
            String text;

            while ((text = buffReader.readLine()) != null) {
                this.content.add(text);
            }
        } catch (IOException e) {
            throw new Exception(e);
        }
    }


}
