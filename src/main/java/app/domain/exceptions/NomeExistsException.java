package app.domain.exceptions;

public class NomeExistsException extends Exception {
    public NomeExistsException(String mensagem){
        super(mensagem);
    }
}
