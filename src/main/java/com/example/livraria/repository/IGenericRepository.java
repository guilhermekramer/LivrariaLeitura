package com.example.livraria.repository;

import com.example.livraria.domain.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface IGenericRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

}
