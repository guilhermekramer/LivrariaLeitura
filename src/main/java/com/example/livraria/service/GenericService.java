package com.example.livraria.service;

import jakarta.persistence.EntityNotFoundException;
import com.example.livraria.domain.AbstractEntity;
import com.example.livraria.repository.IGenericRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public abstract class GenericService<E extends AbstractEntity, R extends IGenericRepository> implements IGenericService<E>{

    R repository;

    public GenericService(R repository) {
        this.repository = repository;
    }



    @Override
    public E getById(Long id) {
        Optional<E> entityBanco = repository.findById(id);
        if (entityBanco.isPresent()){
            return (E) entityBanco.get();
        }else{
            throw  new EntityNotFoundException();
        }
    }

    @Override
    public E create(E e) {
        e.setCreatedAt(LocalDateTime.now());
        return (E) this.repository.save(e);
    }


    @Override
    public E update(E updatedEntity, Long id) {
        Optional<E> entity = repository.findById(id);
        if (entity.isPresent()){
            E e = entity.get();
            e.partialUpdate(updatedEntity);
            e.setAtualizadoAt(LocalDateTime.now());
            E save = (E) this.repository.save(e);
            return save;
        }else{
            throw  new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {

        this.repository.deleteById(id);
    }

    @Override
    public List<E> list() {
        return (List<E>) this.repository.findAll();
    }
}
