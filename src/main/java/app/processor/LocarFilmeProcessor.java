package app.processor;

import app.domain.MetadadosServico;
import app.model.Filme;
import app.model.PostFilmeResponse;
import app.repository.FilmeRespository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LocarFilmeProcessor implements Processor {

    @Autowired
    private FilmeRespository filmeRespository;

    public void process(Exchange exchange) {
        try {
            String nomeFilme = exchange.getIn().getHeader("nomeFilme", String.class);
            Iterable<Filme> filmes = filmeRespository.findByTitulo(nomeFilme);
            Filme filme = filmes.iterator().hasNext() ? filmes.iterator().next() : null;
            if (Objects.isNull(filme)) {
                exchange.getOut().setBody(createResponse(createMeta(false, null), filmes));
                exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.NOT_FOUND);
            } else if (filme.getQntFilmes() > 0) {
                exchange.getOut().setBody(createResponse(createMeta(true, filme), filmes));
                exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK);
            } else {
                exchange.getOut().setBody(createResponse(createMeta(false, filme), filmes));
                exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private PostFilmeResponse createResponse(MetadadosServico metadadosServico, Iterable<Filme> filmes) {
        return PostFilmeResponse.builder()
                .metadadosServico(metadadosServico)
                .filmes(filmes).build();
    }

    private MetadadosServico createMeta(boolean disponivel, Filme filme) {
        if (Objects.isNull(filme))
            return MetadadosServico.builder()
                    .code("HTTP")
                    .mensagem("Não foi possivel encontrar um filme com esse titulo").build();
        if (disponivel) {
            filme.setQntFilmes(filme.getQntFilmes()-1);
            filmeRespository.save(filme);
            return MetadadosServico.builder()
                    .code("HTTP")
                    .mensagem(String.format("O Filme %s foi locado com sucesso", filme.getTitulo())).build();

        } else {
            return MetadadosServico.builder()
                    .code("HTTP")
                    .mensagem(String.format("O Filme %s está com todas as cópias locadas", filme.getTitulo())).build();
        }
    }
}
