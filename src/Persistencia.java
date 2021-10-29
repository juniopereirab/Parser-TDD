import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private List<String> inn = new ArrayList<String>();
    private String outt;
    public List<Integer> convo = new ArrayList<Integer>();

    public String getOutFile() {
        return this.outt;
    }

    public Persistencia(String target_file) {
        this.outt = target_file;
    }

    public List<String> getContent() {
        return this.inn;
    }

    public boolean getFileContent(Parser p) {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(this.outt));
            String line;
            while((line = reader.readLine()) != null) {
                inn.add(line);
            }
            reader.close();
            return true;

        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean saveParsedData(String out_path, String data) {
        try {
            FileWriter writer = new FileWriter(out_path);
            writer.write(data);
            writer.close();
            return true;
        }
        catch(IOException e) {
            return false;
        }
    }
}