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

import java.util.Objects;

@Component
public class DevolverFilmeProcessor implements Processor {

    @Autowired
    private FilmeRespository filmeRespository;

    public void process(Exchange exchange) {
        String nomeFilme = exchange.getIn().getHeader("nomeFilme", String.class);
        Iterable<Filme> filmes = filmeRespository.findByTitulo(nomeFilme);
        Filme filme = filmes.iterator().hasNext() ? filmes.iterator().next() : null;
        if (Objects.isNull(filme)) {
            exchange.getOut().setBody(createResponse(createMeta(null), filmes));
        } else {
            exchange.getOut().setBody(createResponse(createMeta(filme), filmes));
        }
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
    }

    private FilmeResponse createResponse(MetadadosServico metadadosServico, Iterable<Filme> filmes) {
        return FilmeResponse.builder()
                .metadadosServico(metadadosServico)
                .filmes(filmes).build();
    }

    private MetadadosServico createMeta(Filme filme) {
        if (Objects.isNull(filme))
            return MetadadosServico.builder()
                    .code("ERR")
                    .mensagem("Não foi possivel encontrar um filme com esse titulo").build();
        filme.setQntFilmes(filme.getQntFilmes() + 1);
        filmeRespository.save(filme);
        return MetadadosServico.builder()
                .code("HTTP")
                .mensagem(String.format("O Filme %s foi devolvido com sucesso", filme.getTitulo())).build();
    }
}
