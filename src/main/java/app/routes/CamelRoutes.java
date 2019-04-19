package app.routes;

import app.processor.ListagemFilmesProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelRoutes extends RouteBuilder {
    private ListagemFilmesProcessor listagemFilmesProcessor;

    @Autowired
    public CamelRoutes(ListagemFilmesProcessor listagemFilmesProcessor) {
        this.listagemFilmesProcessor = listagemFilmesProcessor;
    }

    @Override
    public void configure() {

        rest()
                .get("/v1/filmes")
                .to("listagemFilmesProcessor");

        from("listagemFilmesProcessor")
                .process(listagemFilmesProcessor)
                .marshal().json().endRest();
    }
}
