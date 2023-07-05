package com.example.livraria.service;

import com.example.livraria.domain.AbstractEntity;

import java.util.List;

public interface IGenericService<E extends AbstractEntity> {

    public E create(E e);
    public E update(E e, Long id);
    public void delete(Long id);
    public List<E> list();
    public E getById(Long id);


}