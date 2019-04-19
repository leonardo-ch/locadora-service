package app.processor;

import app.domain.MetadadosServico;
import app.model.PostFilmeResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ExceptionProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        exchange.getOut().setBody(createResponse(createMeta(cause)));
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private PostFilmeResponse createResponse(MetadadosServico metadadosServico) {
        return PostFilmeResponse.builder()
                .metadadosServico(metadadosServico)
                .filmes(new ArrayList<>()).build();
    }

    private MetadadosServico createMeta(Exception e) {
        return MetadadosServico.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .mensagem(e.getMessage()).build();
    }
}
