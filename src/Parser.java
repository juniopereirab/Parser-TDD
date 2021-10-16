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

    private String parseHorizontally(String result) {
        int dump = 0;
        int counter = 1;

        for (int i = 0; i < this.content.size(); i++) {
            if (this.content.get(i).contains("-----") && i != 0) {
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
}
