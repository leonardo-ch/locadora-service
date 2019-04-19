package app.processor;

import app.model.Filme;
import app.repository.FilmeRespository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListagemFilmesProcessor implements Processor {

    @Autowired
    private FilmeRespository filmeRespository;

    public void process(Exchange exchange) throws Exception {
        Iterable<Filme> filmes = filmeRespository.findAll();
        exchange.getOut().setBody(filmes);
    }
}
