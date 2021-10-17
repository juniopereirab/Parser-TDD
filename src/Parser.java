import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class Parser {
    private char orientation;
    private int iteration;
    private char delimitator;
    private List<String> content = new ArrayList<String>();
    private List<Integer> analises = new ArrayList<Integer>();
//    private AnalysisFile file;

    public Parser(String file, char orientation, char delimitator) {
        this.delimitator = delimitator;
        this.orientation = orientation;
    }

    private String transpose(List<List<String>> convolutions, int max_index) {
        String converted = new String("");

        for (int i = 0; i < max_index; i++) {
            for (int j = 0; j < convolutions.size(); j++) {
                try {
                    converted = converted.concat(convolutions.get(j).get(i) + this.delimitator);
                } catch(Exception excpt){
                    converted = converted.concat(String.valueOf(this.delimitator));
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

        for (int i = 0; i < this.content.size(); i++) {
            if (i != 0 && this.content.get(i).contains("-----")) {
                result = result.concat("\n");
                this.analises.add(dump);
                dump = 0;
            }

            if (this.content.get(i).contains("-----")) {
                String iteration = NumberFormat.getInstance().format(counter) + this.delimitator;
                result = result.concat(iteration);
                counter++;
            } else {
                result = result.concat(this.content.get(i) + this.delimitator);
                dump++;
            }
        }

        this.analises.add(dump);
        this.iteration = counter;
        return result;
    }

    private String parseVertically(String tgt) {
        String[] split_str = tgt.split(String.valueOf(this.delimitator));
        List<List<String>> convolutions = new ArrayList<List<String>>();
        List<String> convo_dump = new ArrayList<String>();

        String col = new String("");
        String res = new String("");

        for (int i = 0; i < split_str.length; i++) {
            if (i == split_str.length - 1)
                convolutions.add(convo_dump);

            if (i == 0)
                col = col.concat(split_str[i] + this.delimitator);

            else if (split_str[i].contains("\n")) {
                col = col.concat(split_str[i].replace("\n", "") + this.delimitator);
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
        tgt = tgt.replaceAll(this.delimitator + "$", "");
        tgt = tgt.replaceAll(this.delimitator + "\n", "\n");

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
}
