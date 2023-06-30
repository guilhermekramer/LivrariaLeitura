package com.example.livraria.service;

import org.springframework.stereotype.Service;
import com.example.livraria.domain.Endereco;
import com.example.livraria.repository.EnderecoRepository;


@Service
public class EnderecoService extends GenericService<Endereco, EnderecoRepository>  {
    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }
}
