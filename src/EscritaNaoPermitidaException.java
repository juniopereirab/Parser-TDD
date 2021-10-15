import java.io.IOException;

public class EscritaNaoPermitidaException extends RuntimeException {
    public EscritaNaoPermitidaException(String message) {
        super(message);
    }
}
