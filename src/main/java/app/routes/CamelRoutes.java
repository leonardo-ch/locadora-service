package app.routes;

import app.processor.*;
import org.apache.camel.builder.RouteBuilder;
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
    private LoginUsuarioProcessor loginUsuarioProcessor;
    private TokenProcessor tokenProcessor;

    @Autowired
    public CamelRoutes(ListagemFilmesProcessor listagemFilmesProcessor, LocarFilmeProcessor locarFilmeProcessor,
                       ExceptionProcessor exceptionProcessor, DevolverFilmeProcessor devolverFilmeProcessor,
                       PesquisarFilmeProcessor pesquisarFilmeProcessor, CriarUsuarioProcessor criarUsuarioProcessor,
                       LoginUsuarioProcessor loginUsuarioProcessor, TokenProcessor tokenProcessor) {
        this.listagemFilmesProcessor = listagemFilmesProcessor;
        this.locarFilmeProcessor = locarFilmeProcessor;
        this.exceptionProcessor = exceptionProcessor;
        this.devolverFilmeProcessor = devolverFilmeProcessor;
        this.pesquisarFilmeProcessor = pesquisarFilmeProcessor;
        this.criarUsuarioProcessor = criarUsuarioProcessor;
        this.loginUsuarioProcessor = loginUsuarioProcessor;
        this.tokenProcessor = tokenProcessor;
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
                .to("direct:tokenProcessor");
        from("direct:tokenProcessor")
                .process(tokenProcessor)
                .to("direct:listagemFilmesProcessor");
        from("direct:listagemFilmesProcessor")
                .process(listagemFilmesProcessor)
                .endRest();

        rest()
                .post("v1/locarFilme")
                .to("direct:tokenProcessor2");
        from("direct:tokenProcessor2")
                .process(tokenProcessor)
                .to("direct:locarFilmeProcessor");
        from("direct:locarFilmeProcessor")
                .process(locarFilmeProcessor)
                .endRest();

        rest()
                .post("v1/devolverFilme")
                .to("direct:tokenProcessor3");
        from("direct:tokenProcessor3")
                .process(tokenProcessor)
                .to("direct:devolverFilmeProcessor");
        from("direct:devolverFilmeProcessor")
                .process(devolverFilmeProcessor)
                .endRest();

        rest()
                .get("v1/pesquisarFilme")
                .to("direct:tokenProcessor4");
        from("direct:tokenProcessor4")
                .process(tokenProcessor)
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

        rest()
                .post("v1/loginUsuario")
                .to("direct:loginUsuarioProcessor");
        from("direct:loginUsuarioProcessor")
                .process(loginUsuarioProcessor)
                .endRest();
    }
}
