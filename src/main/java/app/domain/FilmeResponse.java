package app.domain;

import app.model.Filme;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmeResponse {
    @JsonProperty("metadadosServico")
    private MetadadosServico metadadosServico;
    @JsonProperty("filmes")
    private Iterable<Filme> filmes;
}
