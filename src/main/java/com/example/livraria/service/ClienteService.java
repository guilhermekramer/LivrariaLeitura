package com.example.livraria.service;

import org.springframework.stereotype.Service;
import com.example.livraria.domain.Cliente;
import com.example.livraria.repository.ClienteRepository;

import java.time.LocalDateTime;


@Service
public class ClienteService extends GenericService<Cliente, ClienteRepository>  {
    public ClienteService(ClienteRepository repository) {
        super(repository);
    }

    @Override
    public Cliente create(Cliente cliente) {
        cliente.setCreatedAt(LocalDateTime.now());
        return super.create(cliente);
    }
}
