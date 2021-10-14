public class FileHandler {

    private char delimiter;

    public void setDelimiter(String delimiter) {
        if(delimiter.length() != 1){
            throw new DelimitadorInvalidoException("Delimitador deve ser apenas um caracter");
        }
        this.delimiter = delimiter.charAt(0);
    }

    public char getDelimiter() {
        return delimiter;
    }
}
