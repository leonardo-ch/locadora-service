package app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "filme", schema = "locadora")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id = null;

    @Column(name = "titulo")
    @JsonProperty("titulo")
    private String titulo = null;

    @Column(name = "diretor")
    @JsonProperty("diretor")
    private String diretor = null;

    @Column(name = "qnt_filmes")
    @JsonProperty("qnt_filmes")
    private Integer qntFilmes = null;

    public Filme id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * ID do Filme
     *
     * @return id
     **/
    @JsonProperty("id")
    @ApiModelProperty(value = "ID do Filme")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Filme titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    /**
     * Titulo do Filme
     *
     * @return titulo
     **/
    @JsonProperty("titulo")
    @ApiModelProperty(value = "Titulo do Filme")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Filme diretor(String diretor) {
        this.diretor = diretor;
        return this;
    }

    /**
     * Diretor do Filme
     *
     * @return diretor
     **/
    @JsonProperty("diretor")
    @ApiModelProperty(value = "Diretor do Filme")
    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Filme qntFilmes(Integer qntFilmes) {
        this.qntFilmes = qntFilmes;
        return this;
    }

    /**
     * Quantidade de Copias do Filme
     *
     * @return qntFilmes
     **/
    @JsonProperty("qnt_filmes")
    @ApiModelProperty(value = "Quantidade de Copias do Filme")
    public Integer getQntFilmes() {
        return qntFilmes;
    }

    public void setQntFilmes(Integer qntFilmes) {
        this.qntFilmes = qntFilmes;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Filme filme = (Filme) o;
        return Objects.equals(this.id, filme.id) &&
                Objects.equals(this.titulo, filme.titulo) &&
                Objects.equals(this.diretor, filme.diretor) &&
                Objects.equals(this.qntFilmes, filme.qntFilmes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, diretor, qntFilmes);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Filme {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
        sb.append("    diretor: ").append(toIndentedString(diretor)).append("\n");
        sb.append("    qntFilmes: ").append(toIndentedString(qntFilmes)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
