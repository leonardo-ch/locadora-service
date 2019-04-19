package app.routes;

import app.processor.ExceptionProcessor;
import app.processor.ListagemFilmesProcessor;
import app.processor.LocarFilmeProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CamelRoutes extends RouteBuilder {
    private ListagemFilmesProcessor listagemFilmesProcessor;
    private LocarFilmeProcessor locarFilmeProcessor;
    private ExceptionProcessor exceptionProcessor;

    @Autowired
    public CamelRoutes(ListagemFilmesProcessor listagemFilmesProcessor, LocarFilmeProcessor locarFilmeProcessor, ExceptionProcessor exceptionProcessor) {
        this.listagemFilmesProcessor = listagemFilmesProcessor;
        this.locarFilmeProcessor = locarFilmeProcessor;
        this.exceptionProcessor = exceptionProcessor;
    }

    @Override
    public void configure() {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        onException(Exception.class)
                .handled(true)
                .to("direct:exception");

        rest()
                .get("/v1/filmes")
                .to("direct:listagemFilmesProcessor");

        from("direct:listagemFilmesProcessor")
                .process(listagemFilmesProcessor)
                .endRest();

        rest()
                .post("v1/locarFilme")
                .to("direct:locarFilmeProcessor");

        from("direct:locarFilmeProcessor")
                .process(locarFilmeProcessor)
                .endRest();

        from("direct:exception")
                .process(exceptionProcessor)
                .endRest();
    }
}
