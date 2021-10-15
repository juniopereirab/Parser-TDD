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

    public void setWriter(String outputPath) {
        try {
            FileWriter writer = new FileWriter(outputPath);
            this.writer = writer;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public FileWriter getWriter() {
        return writer;
    }
}
