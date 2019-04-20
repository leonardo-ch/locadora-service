package app.routes;

import app.model.Usuario;
import app.processor.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CamelRoutes extends RouteBuilder {
    private ListagemFilmesProcessor listagemFilmesProcessor;
    private LocarFilmeProcessor locarFilmeProcessor;
    private ExceptionProcessor exceptionProcessor;
    private DevolverFilmeProcessor devolverFilmeProcessor;
    private PesquisarFilmeProcessor pesquisarFilmeProcessor;
    private CriarUsuarioProcessor criarUsuarioProcessor;

    @Autowired
    public CamelRoutes(ListagemFilmesProcessor listagemFilmesProcessor, LocarFilmeProcessor locarFilmeProcessor,
                       ExceptionProcessor exceptionProcessor, DevolverFilmeProcessor devolverFilmeProcessor,
                       PesquisarFilmeProcessor pesquisarFilmeProcessor, CriarUsuarioProcessor criarUsuarioProcessor) {
        this.listagemFilmesProcessor = listagemFilmesProcessor;
        this.locarFilmeProcessor = locarFilmeProcessor;
        this.exceptionProcessor = exceptionProcessor;
        this.devolverFilmeProcessor = devolverFilmeProcessor;
        this.pesquisarFilmeProcessor = pesquisarFilmeProcessor;
        this.criarUsuarioProcessor = criarUsuarioProcessor;
    }

    @Override
    public void configure() {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        onException(Exception.class)
                .handled(true)
                .process(exceptionProcessor)
                .end();

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

        rest()
                .post("v1/devolverFilme")
                .to("direct:devolverFilmeProcessor");
        from("direct:devolverFilmeProcessor")
                .process(devolverFilmeProcessor)
                .endRest();

        rest()
                .get("v1/pesquisarFilme")
                .to("direct:pesquisarFilmeProcessor");
        from("direct:pesquisarFilmeProcessor")
                .process(pesquisarFilmeProcessor)
                .endRest();

        rest()
                .post("v1/criarUsuario")
                .to("direct:criarUsuarioProcessor");
        from("direct:criarUsuarioProcessor")
                .process(criarUsuarioProcessor)
                .endRest();
    }
}
