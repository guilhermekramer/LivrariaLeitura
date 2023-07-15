package com.example.livraria.service;

import com.example.livraria.domain.AbstractEntity;
import com.example.livraria.domain.Cliente;
import com.example.livraria.domain.Livro;
import com.example.livraria.domain.Pedido;
import com.example.livraria.repository.ClienteRepository;
import com.example.livraria.repository.LivroRepository;
import com.example.livraria.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService extends GenericService <Pedido, PedidoRepository>{

    ClienteRepository clienteRepository;
    LivroRepository livroRepository;

    public PedidoService(PedidoRepository repository) {
        super(repository);
    }




    @Override
    public Pedido create(Pedido pedido) {

        if(pedido.getCliente() == null){
            throw new EntityNotFoundException();
        }

        repository.save(pedido);
        return super.create(pedido);
    }





}
