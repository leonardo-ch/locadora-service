package app.repository;

import app.model.Filme;
import app.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRespository extends CrudRepository<Usuario, Integer> {

    Iterable<Usuario> findByNome(String nome);
    Iterable<Usuario> findByEmail(String email);
}
