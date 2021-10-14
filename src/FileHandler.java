public class FileHandler {

    private String delimiter;

    public void setDelimiter(String delimiter) {
        if(delimiter.length() != 1){
            throw new DelimitadorInvalidoException("Delimitador deve ser apenas um caracter");
        }
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
