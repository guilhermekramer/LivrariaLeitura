package com.example.livraria.repository;

import com.example.livraria.domain.AbstractEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IGenericRepository<E extends AbstractEntity> extends ListCrudRepository<E, Long> {


}
