import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner i = new Scanner(System.in);
            AnalysisFile entryFile = new AnalysisFile();
            FileHandler handler = new FileHandler();
            Parser p = new Parser();

            String in;
            String out;
            String delimiter;
            char orientation;

            System.out.println("Escreva o caminho do arquivo de entrada: ");
            in = i.nextLine();
            entryFile.openTimeAnalysis(in);
            entryFile.getDataFromFile();

            System.out.println("Escreva o separador desejado. Apenas um caracter é aceito: ");
            delimiter = i.nextLine();
            p.setDelimiter(delimiter);

            System.out.println("Escolha a opção de orientação da saída dos dados: use 'v' para vertical e 'h' para horizontal: ");
            orientation = i.nextLine().charAt(0);
            p.setOrientation(orientation);

            System.out.println("Escreva o caminho do arquivo de saída: ");
            out = i.nextLine();
            handler.setWriter(out);

            p.setContent(entryFile.getContent());
            String parsedData = p.getParsedData();

            handler.setResult(parsedData);
            handler.writeFile();

            printAnalysis(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printAnalysis (Parser p) {
        System.out.println("Arquivo parseado.");
        System.out.printf("Iterações: %d\n", p.getIteration());
        List<Integer> analises = p.getAnalises();
        for (int j = 0; j < analises.size(); j++) {
            System.out.printf("Convolução %d: %d analises pontuais\n", j+1, analises.get(j));
        }
    }
}
