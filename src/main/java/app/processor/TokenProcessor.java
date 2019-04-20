package app.processor;

import app.domain.TokenUserObject;
import app.domain.exceptions.ParameterExistsException;
import app.model.Usuario;
import app.repository.UsuarioRespository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TokenProcessor implements Processor {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Override
    public void process(Exchange exchange) throws Exception {
        String jwtToken = exchange.getIn().getHeader("Authorization", String.class);
        if (Objects.isNull(jwtToken)) {
            throw new ParameterExistsException("Parametro faltante: Authorization");
        }

        //Base64
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        ObjectMapper objectMapper = new ObjectMapper();
        TokenUserObject tokenUserObject = objectMapper.readValue(body, TokenUserObject.class);
        Usuario usuario = usuarioRespository.findByNome(tokenUserObject.getUserId()).iterator().next();

        //JWT
        Algorithm algorithm = Algorithm.HMAC256(usuario.getSenha());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("jwtauth")
                .build();
        verifier.verify(jwtToken);
    }
}
