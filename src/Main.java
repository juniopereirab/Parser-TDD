import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        AnalysisFile entryFile = new AnalysisFile();
        FileHandler handle = new FileHandler();
        Parser p = new Parser();

        String in = new String("");
        String out = new String("");
        String dump;
        String delimitator;
        char orientation;

        System.out.println("Escreva o caminho do arquivo de entrada: ");
        in = i.nextLine();
        entryFile.openTimeAnalysis(in);

        System.out.println("Escreva o separador desejado. Apenas um caracter é aceito: ");
        delimitator = i.nextLine();
        p.setDelimiter(delimitator);

        System.out.println("Escolha a opção de orientação da saída dos dados: use 'v' para vertical e 'h' para horizontal: ");
        orientation = i.nextLine().charAt(0);
        p.setOrientation(orientation);

        System.out.println("Escreva o caminho do arquivo de saída: ");
        out = i.nextLine();

        p = new Parser();
        p.setHandler(handle);

        if (true) {
            p.setContent(entryFile.getContent());
            String parsedData = p.getParsedData();
            if (true) {
                System.out.println("Arquivo parseado.");
                System.out.printf("Iterações: %d\n", p.getIteration());

                List<Integer> analises = p.getAnalises();

                for (int j = 0; j < analises.size(); j++) {
                    System.out.printf("Convolução %d: %d analises pontuais\n", j+1, analises.get(j));
                }
            }
        } else {
            System.out.println("Erro ao abrir arquivo.");
        }
    }
}
