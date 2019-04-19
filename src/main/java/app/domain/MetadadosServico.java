package app.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetadadosServico {
    private String code;
    private String mensagem;
}
