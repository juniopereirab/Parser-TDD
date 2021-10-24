import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class Parser {
    private char orientation;
    private char delimiter;
    private int iteration;
    private List<String> content = new ArrayList<>();
    private List<Integer> analises = new ArrayList<>();

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
                col = col.concat(split_str[i] + delimiter);

            else if (split_str[i].contains("\n")) {
                col = col.concat(split_str[i].replace("\n", "") + delimiter);
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

        for (int i = 0; i < content.size(); i++) {
            if (i != 0 && content.get(i).contains("-----")) {
                result = result.concat("\n");
                analises.add(dump);
                dump = 0;
            }

            if (content.get(i).contains("-----")) {
                String iteration = NumberFormat.getInstance().format(counter) + delimiter;
                result = result.concat(iteration);
                counter++;
            } else {
                result = result.concat(content.get(i) + delimiter);
                dump++;
            }
        }

        analises.add(dump);
        iteration = counter;
        return result;
    }

    private String transpose(List<List<String>> convolutions, int max_index) {
        String converted = "";

        for (int i = 0; i < max_index; i++) {
            for (int j = 0; j < convolutions.size(); j++) {
                try {
                    converted = converted.concat(convolutions.get(j).get(i) + delimiter);
                } catch(Exception excpt){
                    converted = converted.concat(String.valueOf(delimiter));
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
        tgt = tgt.replaceAll(delimiter + "$", "");
        tgt = tgt.replaceAll(delimiter + "\n", "\n");

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
        return delimiter;
    }

    public int getIteration() {
        return this.iteration - 1;
    }

    public List<Integer> getAnalises() { return this.analises; }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
