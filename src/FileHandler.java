import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private FileWriter writer;
    private String result;

    public void setWriter(String outputPath) throws EscritaNaoPermitidaException {
        try {
            FileWriter writer = new FileWriter(outputPath);
            this.writer = writer;
        } catch (IOException e) {
            throw new EscritaNaoPermitidaException("Escrita não autorizada");
        }
    }

    public FileWriter getWriter() {
        return writer;
    }

    public void writeFile() throws EscritaNaoPermitidaException {
        try {
            BufferedWriter buffWrite = new BufferedWriter(writer);
            buffWrite.append(result);
            buffWrite.close();
        }
        catch(IOException e){
            throw new EscritaNaoPermitidaException("Escrita não autorizada");
        }
    }

    public void setResult(String result) { this.result = result; }
}
