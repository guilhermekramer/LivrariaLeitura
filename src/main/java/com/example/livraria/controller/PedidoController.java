package com.example.livraria.controller;


import com.example.livraria.domain.Cliente;
import com.example.livraria.domain.Livro;
import com.example.livraria.domain.Pedido;
import com.example.livraria.repository.ClienteRepository;
import com.example.livraria.repository.LivroRepository;
import com.example.livraria.repository.PedidoRepository;
import com.example.livraria.service.PedidoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")

public class PedidoController {

    PedidoService service;
    ModelMapper mapper;

    ClienteRepository clienteRepository;

    LivroRepository livroRepository;


    public PedidoController(PedidoService service, ModelMapper mapper,  ClienteRepository clienteRepository, LivroRepository livroRepository) {
        this.service = service;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
        this.livroRepository = livroRepository;

    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido.DtoResponse create(@RequestBody @Valid Pedido.DtoRequest p){

        Optional<Cliente> clienteOptional = clienteRepository.findById(p.getClienteId());
        List<Livro> livros = livroRepository.findAllById(p.getLivrosId());
        Pedido ped = new Pedido();
        ped.setLivros(livros);
        ped.setCliente(clienteOptional.get());
        Pedido pedido = this.service.create(ped);

        Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(pedido, mapper);


        response.generateLinks(pedido.getId());

        return response;
    }



    @GetMapping
    public List<Pedido.DtoResponse> list(){

        return this.service.list().stream().map(
                elementoAtual -> {
                    Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(elementoAtual, mapper);
                    response.generateLinks(elementoAtual.getId());
                    return response;
                }).toList();
    }

    @GetMapping("{id}")
    public Pedido.DtoResponse getById(@PathVariable Long id){
        Pedido pedido = this.service.getById(id);
        Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(pedido, mapper);
        response.generateLinks(pedido.getId());

        return response;
    }
    @PutMapping("{id}")
    public Pedido.DtoResponse update(@RequestBody Pedido.DtoRequest dtoRequest, @PathVariable Long id){
        Pedido p = Pedido.DtoRequest.convertToEntity(dtoRequest, mapper);
        Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(this.service.update(p,id), mapper);
        response.generateLinks(id);
        return response;
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}
