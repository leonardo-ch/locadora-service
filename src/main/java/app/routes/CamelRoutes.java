package app.routes;

import app.processor.ListagemFilmesProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
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
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest()
                .get("/v1/filmes")
                .to("direct:listagemFilmesProcessor");

        from("direct:listagemFilmesProcessor")
                .process(listagemFilmesProcessor)
                .endRest();
    }
}
