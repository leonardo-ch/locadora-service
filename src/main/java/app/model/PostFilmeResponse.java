package app.model;

import app.domain.MetadadosServico;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostFilmeResponse {
    @JsonProperty("metadadosServico")
    private MetadadosServico metadadosServico;
    @JsonProperty("filmes")
    private Iterable<Filme> filmes;
}
