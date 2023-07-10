package com.example.livraria.service;

import com.example.livraria.domain.AbstractEntity;
import com.example.livraria.domain.Pedido;
import com.example.livraria.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends GenericService <Pedido, PedidoRepository>{

    public PedidoService(PedidoRepository repository) {
        super(repository);
    }
}
