package app.domain;

import app.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {
    @JsonProperty("metadadosServico")
    private MetadadosServico metadadosServico;
    @JsonProperty("usuario")
    private Usuario usuario;
}
