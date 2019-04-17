package com.repository;

import com.model.Filme;
import org.springframework.data.repository.CrudRepository;

public interface FilmeRespository extends CrudRepository<Filme, Integer> {
}
