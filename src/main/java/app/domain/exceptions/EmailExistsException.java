package app.domain.exceptions;

public class EmailExistsException extends Exception {
    public EmailExistsException(String mensagem){
        super(mensagem);
    }
}
