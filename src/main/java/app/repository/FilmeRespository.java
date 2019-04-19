package app.repository;

import app.model.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRespository extends CrudRepository<Filme, Integer> {
}
