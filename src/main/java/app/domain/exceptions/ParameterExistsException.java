package app.domain.exceptions;

public class ParameterExistsException extends Exception {
    public ParameterExistsException(String mensagem){
        super(mensagem);
    }
}
