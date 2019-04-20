package app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "locadora")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id = null;

    @Column(name = "nome")
    @JsonProperty("nome")
    private String nome = null;

    @Column(name = "email")
    @JsonProperty("email")
    private String email = null;

    @Column(name = "senha")
    @JsonProperty("senha")
    private String senha = null;

    public Usuario id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * ID do Usuário
     *
     * @return id
     **/
    @JsonProperty("id")
    @ApiModelProperty(value = "ID do Usuário")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario nome(String nome) {
        this.nome = nome;
        return this;
    }

    /**
     * Nome do Usuário
     *
     * @return nome
     **/
    @JsonProperty("nome")
    @ApiModelProperty(value = "Nome do Usuário")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Email do Usuário
     *
     * @return email
     **/
    @JsonProperty("email")
    @ApiModelProperty(value = "Email do Usuário")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario senha(String senha) {
        this.senha = senha;
        return this;
    }

    /**
     * Senha do Usuário
     *
     * @return senha
     **/
    @JsonProperty("senha")
    @ApiModelProperty(value = "Senha do Usuário")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(this.id, usuario.id) &&
                Objects.equals(this.nome, usuario.nome) &&
                Objects.equals(this.email, usuario.email) &&
                Objects.equals(this.senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Usuario {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
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
