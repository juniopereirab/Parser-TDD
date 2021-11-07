import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner i = new Scanner(System.in);

            String in = new String("");
            String out = new String("");
            String delimiter;
            char delimiter_char;
            char orientation;

            System.out.println("Escreva o caminho do arquivo de entrada: ");
            in = i.nextLine();

            System.out.println("Escreva o separador desejado. Apenas um caracter é aceito: ");
            delimiter = i.nextLine();
            delimiter_char = delimiter.charAt(0);

            System.out.println("Escolha a opção de orientação da saída dos dados: use 'v' para vertical e 'h' para horizontal: ");
            orientation = i.nextLine().charAt(0);

            System.out.println("Escreva o caminho do arquivo de saída: ");
            out = i.nextLine();
            Parser p = new Parser(delimiter_char, orientation, in);
            if(p.getFileContent()) {
                String parsedData = p.getParsedData();
                if (p.saveParsedData(out, parsedData)) {
                    printAnalysis(p);
                } else {
                    throw new EscritaNaoPermitidaException("Escrita não autorizada");
                }
            }


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
