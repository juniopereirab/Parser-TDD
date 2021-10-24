import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        FileHandler handle = new FileHandler();
        Parser p;

        String in = new String("");
        String out = new String("");
        String dump;
        char delimitator;
        char orientation;

        System.out.println("Escreva o caminho do arquivo de entrada: ");
        in = i.nextLine();

        System.out.println("Escreva o separador desejado. Apenas um caracter é aceito: ");
        dump = i.nextLine();
        handle.setDelimiter(dump);

        System.out.println("Escolha a opção de orientação da saída dos dados: use 'v' para vertical e 'h' para horizontal: ");
        orientation = i.nextLine().charAt(0);

        System.out.println("Escreva o caminho do arquivo de saída: ");
        out = i.nextLine();

        p = new Parser(in, orientation);
        p.setHandler(handle);

        if (p.getDataFromFile()) {
            String parsedData = p.getParsedData();
            if (p.saveParsedData(parsedData, out)) {
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
