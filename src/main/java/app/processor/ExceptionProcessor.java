package app.processor;

import app.domain.GenericResponse;
import app.domain.MetadadosServico;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        exchange.getOut().setBody(createResponse(createMeta(cause)));
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GenericResponse createResponse(MetadadosServico metadadosServico) {
        return GenericResponse.builder()
                .metadadosServico(metadadosServico).build();
    }

    private MetadadosServico createMeta(Exception e) {
        return MetadadosServico.builder()
                .code("ERR")
                .mensagem(e.getMessage()).build();
    }
}
