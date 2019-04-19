package app.repository;

import app.model.Filme;
import org.springframework.data.repository.CrudRepository;

public interface FilmeRespository extends CrudRepository<Filme, Integer> {
}
