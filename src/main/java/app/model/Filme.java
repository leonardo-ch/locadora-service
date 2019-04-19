package app.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "filme", schema = "locadora")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "titulo")
    private String titulo;
    @NotNull
    @Column(name = "diretor")
    private String diretor;
    @NotNull
    @Column(name = "qnt_filmes")
    private Integer qntFilmes;
}
