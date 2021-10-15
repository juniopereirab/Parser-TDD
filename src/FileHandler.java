import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private char delimiter;
    private FileWriter writer;

    public void setDelimiter(String delimiter) {
        if(delimiter.length() != 1){
            throw new DelimitadorInvalidoException("Delimitador deve ser apenas um caracter");
        }
        this.delimiter = delimiter.charAt(0);
    }

    public char getDelimiter() {
        return delimiter;
    }

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

    public void writeFile(String line) throws EscritaNaoPermitidaException {
        try {
            BufferedWriter buffWrite = new BufferedWriter(writer);
            buffWrite.append(line);
            buffWrite.close();
        }
        catch(IOException e){
            throw new EscritaNaoPermitidaException("Escrita não autorizada");
        }
    }
}
