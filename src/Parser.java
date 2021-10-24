import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class Parser {
    private char orientation;
    private int iteration;
    private List<String> content = new ArrayList<String>();
    private List<Integer> analises = new ArrayList<Integer>();
    private AnalysisFile file;
    private FileHandler handler;

    public Parser(String file, char orientation) {
        this.file = new AnalysisFile();
        this.file.openTimeAnalysis(file);
        this.orientation = orientation;
    }

    public void setHandler(FileHandler handler) {
        this.handler = handler;
    }

    public int getIteration() {
        return this.iteration - 1;
    }

    public List<Integer> getAnalises() { return this.analises; }

    public char getOrientation() {
        return this.orientation;
    }

    public boolean getDataFromFile() {
        try {
            BufferedReader buffReader = new BufferedReader(this.file.getTimeAnalysis());

            this.checkAndAddContent(buffReader);

            buffReader.close();
            return true;
        } catch(Exception excpt) {
            return false;
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

    private String transpose(List<List<String>> convolutions, int max_index) {
        String converted = new String("");

        for (int i = 0; i < max_index; i++) {
            for (int j = 0; j < convolutions.size(); j++) {
                try {
                    converted = converted.concat(convolutions.get(j).get(i) + handler.getDelimiter());
                } catch(Exception excpt){
                    converted = converted.concat(String.valueOf(handler.getDelimiter()));
                }
            }

            if (i != max_index - 1)
                converted = converted.concat("\n");
        }

        return converted;
    }

    private int getMaxIndex(List<List<String>> convolutions) {
        int max_index = -1000;

        for (int i = 0; i < convolutions.size(); i++) {
            if (convolutions.get(i).size() > max_index)
                max_index = convolutions.get(i).size();
        }

        return max_index;
    }

    private String parseHorizontally(String result) {
        int dump = 0;
        int counter = 1;

        for (int i = 0; i < content.size(); i++) {
            if (i != 0 && content.get(i).contains("-----")) {
                result = result.concat("\n");
                analises.add(dump);
                dump = 0;
            }

            if (content.get(i).contains("-----")) {
                String iteration = NumberFormat.getInstance().format(counter) + handler.getDelimiter();
                result = result.concat(iteration);
                counter++;
            } else {
                result = result.concat(content.get(i) + handler.getDelimiter());
                dump++;
            }
        }

        analises.add(dump);
        iteration = counter;
        return result;
    }

    private String parseVertically(String tgt) {
        String[] split_str = tgt.split(String.valueOf(handler.getDelimiter()));
        List<List<String>> convolutions = new ArrayList<List<String>>();
        List<String> convo_dump = new ArrayList<String>();

        String col = new String("");
        String res = new String("");

        for (int i = 0; i < split_str.length; i++) {
            if (i == split_str.length - 1)
                convolutions.add(convo_dump);

            if (i == 0)
                col = col.concat(split_str[i] + handler.getDelimiter());

            else if (split_str[i].contains("\n")) {
                col = col.concat(split_str[i].replace("\n", "") + handler.getDelimiter());
                convolutions.add(convo_dump);
                convo_dump = new ArrayList<String>();
            } else {
                convo_dump.add(split_str[i]);
            }
        }

        int max_index = getMaxIndex(convolutions);
        String converted = transpose(convolutions, max_index);
        col = col.concat("\n");
        res = res.concat(col);
        res = res.concat(converted);
        return res;
    }

    public String removeInvalidChars(String tgt) {
        tgt = tgt.replaceAll(handler.getDelimiter() + "$", "");
        tgt = tgt.replaceAll(handler.getDelimiter() + "\n", "\n");

        return tgt;
    }

    public String getParsedData() {
        String res = new String("");
        res = parseHorizontally(res);

        if (this.orientation == 'v') {
            String vertical_parsed = parseVertically(res);
            return removeInvalidChars(vertical_parsed);
        }

        return removeInvalidChars(res);
    }

    public boolean saveParsedData(String parsedRes, String outputPath) {
        try {
            handler.setWriter(outputPath);
            handler.writeFile(parsedRes);

            return true;
        } catch(Exception excpt) {
            return false;
        }

    }
}
