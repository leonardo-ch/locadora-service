package app.domain.exceptions;

public class UsuarioExistsException extends Exception {
    public UsuarioExistsException(String mensagem){
        super(mensagem);
    }
}
