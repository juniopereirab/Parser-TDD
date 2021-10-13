import java.io.FileNotFoundException;

public class ArquivoNaoEncontradoException extends RuntimeException {
    public ArquivoNaoEncontradoException (String message) {
        super("Arquivo " + message + " não encontrado!");
    }
}
