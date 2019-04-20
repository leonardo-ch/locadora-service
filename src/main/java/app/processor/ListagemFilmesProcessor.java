package app.processor;

import app.domain.FilmeResponse;
import app.domain.MetadadosServico;
import app.model.Filme;
import app.repository.FilmeRespository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ListagemFilmesProcessor implements Processor {

    @Autowired
    private FilmeRespository filmeRespository;

    public void process(Exchange exchange) {
        Iterable<Filme> filmes = filmeRespository.findAll();
        if (!filmes.iterator().hasNext()) {
            exchange.getOut().setBody(createResponse(createMeta(filmes), null));
        } else {
            exchange.getOut().setBody(createResponse(createMeta(filmes), filmes));
        }
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
    }

    private FilmeResponse createResponse(MetadadosServico metadadosServico, Iterable<Filme> filmes) {
        return FilmeResponse.builder()
                .metadadosServico(metadadosServico)
                .filmes(filmes).build();
    }

    private MetadadosServico createMeta(Iterable<Filme> filmes) {
        if (!filmes.iterator().hasNext())
            return MetadadosServico.builder()
                    .code("ERR")
                    .mensagem("Não foi possivel encontrar um filmes na locadora").build();
        return MetadadosServico.builder()
                .code("HTTP")
                .mensagem("Filmes encontrados").build();
    }
}
