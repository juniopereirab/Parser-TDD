public class DelimitadorInvalidoException extends RuntimeException {
    public DelimitadorInvalidoException (String message) {
        super("Arquivo " + message + " não encontrado!");
    }
}
