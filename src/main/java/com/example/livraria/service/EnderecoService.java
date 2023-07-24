package com.example.livraria.service;

import com.example.livraria.domain.Endereco;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import com.example.livraria.repository.EnderecoRepository;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class EnderecoService extends GenericService<Endereco, EnderecoRepository>  {

    EnderecoRepository repository;
    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }


//    public Endereco update(Endereco updatedEntity, Long id) {
//        Optional end = repository.findById(id);
//        if(end.isPresent()){
//            Endereco endereco = (Endereco) end.get();
//            endereco.setAtualizadoAt(LocalDateTime.now());
//        }
//        return super.update(updatedEntity, id);
//    }
}
