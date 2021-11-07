import com.sun.jdi.connect.Connector;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class Parser {
    private char orientation;
    private char delimiter;
    private int iteration;
    private Persistencia data;

    public Parser(char delimiter, char orientation, String target_file){
        this.orientation = orientation;
        this.delimiter = delimiter;
        this.data = new Persistencia(target_file);
    }

    public String getParsedData() {
        String res = "";
        res = parseHorizontally(res);

        if (this.orientation == 'v') {
            String vertical_parsed = parseVertically(res);
            return removeInvalidChars(vertical_parsed);
        }

        return removeInvalidChars(res);
    }

    private String parseVertically(String result) {
        String[] split_str = result.split(String.valueOf(delimiter));
        List<List<String>> convolutions = new ArrayList<>();
        List<String> convo_dump = new ArrayList<>();

        String col = "";
        String res = "";

        for (int i = 0; i < split_str.length; i++) {
            if (i == split_str.length - 1)
                convolutions.add(convo_dump);

            if (i == 0)
                col = col.concat(split_str[i] + this.delimiter);

            else if (split_str[i].contains("\n")) {
                col = col.concat(split_str[i].replace("\n", "") + this.delimiter);
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

    private String parseHorizontally(String result) {
        int dump = 0;
        int counter = 1;

        for (int i = 0; i < this.data.getContent().size(); i++) {
            if (i != 0 && this.data.getContent().get(i).contains("-----")) {
                result = result.concat("\n");
                this.data.convo.add(dump);
                dump = 0;
            }

            if (this.data.getContent().get(i).contains("-----")) {
                String iteration = NumberFormat.getInstance().format(counter) + this.delimiter;
                result = result.concat(iteration);
                counter++;
            } else {
                result = result.concat(this.data.getContent().get(i) + this.delimiter);
                dump++;
            }
        }

        this.data.convo.add(dump);
        this.iteration = counter;
        return result;
    }

    private String transpose(List<List<String>> convolutions, int max_index) {
        String converted = "";

        for (int i = 0; i < max_index; i++) {
            for (int j = 0; j < convolutions.size(); j++) {
                try {
                    converted = converted.concat(convolutions.get(j).get(i) + this.delimiter);
                } catch(Exception excpt){
                    converted = converted.concat(String.valueOf(this.delimiter));
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


    public String removeInvalidChars(String tgt) {
        tgt = tgt.replaceAll(this.delimiter + "$", "");
        tgt = tgt.replaceAll(this.delimiter + "\n", "\n");

        return tgt;
    }

    public void setOrientation(String orientation) {
        if(orientation.length() != 1){
            throw new OrientacaoInvalidaException("Orientação deve ter apenas um caracter");
        }

        this.orientation = orientation.charAt(0);
    }

    public char getOrientation() {
        return this.orientation;
    }

    public void setDelimiter(String delimiter) {
        if(delimiter.length() != 1){
            throw new DelimitadorInvalidoException("Delimitador deve ser apenas um caracter");
        }
        this.delimiter = delimiter.charAt(0);
    }

    public char getDelimiter() {
        return this.delimiter;
    }

    public int getIteration() {
        return this.iteration - 1;
    }

    public List<Integer> getAnalises() { return this.data.convo; }

    public boolean getFileContent() {
        return this.data.getFileContent(this);
    }

    public boolean saveParsedData(String out, String parsedData) {
        return this.data.saveParsedData(out, parsedData);
    }
}
