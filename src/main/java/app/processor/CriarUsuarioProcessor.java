package app.processor;

import app.domain.MetadadosServico;
import app.domain.UsuarioResponse;
import app.domain.exceptions.EmailExistsException;
import app.domain.exceptions.NomeExistsException;
import app.domain.exceptions.ParameterExistsException;
import app.model.Usuario;
import app.repository.UsuarioRespository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CriarUsuarioProcessor implements Processor {

    @Autowired
    private UsuarioRespository usuarioRespository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void process(Exchange exchange) throws Exception {
        String nomeUsuario = exchange.getIn().getHeader("nomeUsuario", String.class);
        String emailUsuario = exchange.getIn().getHeader("emailUsuario", String.class);
        String senhaUsuario = exchange.getIn().getHeader("senhaUsuario", String.class);
        validateParameters(nomeUsuario, emailUsuario, senhaUsuario);
        Usuario usuario = Usuario.builder()
                .nome(nomeUsuario)
                .email(emailUsuario)
                .senha(passwordEncoder.encode(senhaUsuario)).build();
        if (Objects.isNull(usuarioRespository.save(usuario))) {
            exchange.getOut().setBody(createResponse(createMeta(null), null));
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
        } else {
            exchange.getOut().setBody(createResponse(createMeta(usuario), usuario));
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.CREATED.value());
        }
    }

    private void validateParameters(String nomeUsuario, String emailUsuario, String senhaUsuario)
            throws ParameterExistsException, EmailExistsException, NomeExistsException {
        if (StringUtils.isBlank(nomeUsuario)) {
            throw new ParameterExistsException("Parametro faltante: nomeUsuario");
        }
        if (StringUtils.isBlank(emailUsuario)) {
            throw new ParameterExistsException("Parametro faltante: emailUsuario");
        }
        if (StringUtils.isBlank(senhaUsuario)) {
            throw new ParameterExistsException("Parametro faltante: senhaUsuario");
        }
        if (emailExist(emailUsuario)) {
            throw new EmailExistsException("Já existe uma conta com esse email: " + emailUsuario);
        }
        if (nomeExist(nomeUsuario)) {
            throw new NomeExistsException("Já existe uma conta com esse nome: " + nomeUsuario);
        }
    }

    private UsuarioResponse createResponse(MetadadosServico metadadosServico, Usuario usuario) {
        return UsuarioResponse.builder()
                .metadadosServico(metadadosServico)
                .usuario(usuario).build();
    }

    private MetadadosServico createMeta(Usuario usuario) {
        if (Objects.isNull(usuario))
            return MetadadosServico.builder()
                    .code("ERR")
                    .mensagem("Não foi possivel criar o usuário").build();
        return MetadadosServico.builder()
                .code("HTTP")
                .mensagem(String.format("O usuário %s foi incluído com sucesso", usuario.getNome())).build();
    }

    private boolean emailExist(String email) {
        return usuarioRespository.findByEmail(email).iterator().hasNext();
    }

    private boolean nomeExist(String nome) {
        return usuarioRespository.findByNome(nome).iterator().hasNext();
    }
}
