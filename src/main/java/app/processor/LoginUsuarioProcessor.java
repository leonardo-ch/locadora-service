package app.processor;

import app.domain.MetadadosServico;
import app.domain.UsuarioResponse;
import app.domain.exceptions.UsuarioExistsException;
import app.model.Usuario;
import app.repository.UsuarioRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class LoginUsuarioProcessor implements Processor {

    @Autowired
    private UsuarioRespository usuarioRespository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void process(Exchange exchange) throws Exception {
        try {
            String nomeUsuario = exchange.getIn().getHeader("nomeUsuario", String.class);
            String senhaUsuario = exchange.getIn().getHeader("senhaUsuario", String.class);
            Iterable<Usuario> filmes = usuarioRespository.findByNome(nomeUsuario);
            Usuario usuario = filmes.iterator().hasNext() ? filmes.iterator().next() : null;
            if (Objects.isNull(usuario)) {
                throw new UsuarioExistsException("Não existe usuário com esse nome: " + nomeUsuario);
            }
            if (passwordEncoder.matches(senhaUsuario, usuario.getSenha())) {
                exchange.getOut().setBody(usuario);
            } else {
                exchange.getOut().setBody(MetadadosServico.builder()
                        .code("ERR")
                        .mensagem("Senha inválida").build());
            }
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
        } catch (
                Exception e) {
            throw e;
        }
    }
}
