package com.example.livraria.service;

import org.springframework.stereotype.Service;
import com.example.livraria.domain.Livro;
import com.example.livraria.repository.LivroRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class LivroService extends GenericService<Livro, LivroRepository>  {
    public LivroService(LivroRepository repository) {
        super(repository);
    }
//
//    @Override
//    public List<Livro> list() {
//        return super.list();
//    }


}
