package com.example.livraria.controller;


import com.example.livraria.domain.Cliente;
import com.example.livraria.domain.Livro;
import com.example.livraria.domain.Pedido;
import com.example.livraria.repository.ClienteRepository;
import com.example.livraria.repository.LivroRepository;
import com.example.livraria.repository.PedidoRepository;
import com.example.livraria.service.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    PedidoService service;
    ModelMapper mapper;

    ClienteRepository clienteRepository;

    LivroRepository livroRepository;

    PedidoRepository pedidoRepository;

    public PedidoController(PedidoService service, ModelMapper mapper, ClienteRepository clienteRepository, LivroRepository livroRepository, PedidoRepository pedidoRepository) {
        this.service = service;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
        this.livroRepository = livroRepository;
        this.pedidoRepository = pedidoRepository;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Pedido p){

        System.out.println(p.toString());
        System.out.println(p.getCliente().toString());

        Cliente c = clienteRepository.findById(p.getCliente().getId()).get();

        System.out.println(c.toString());
        p.setCliente(c);
        System.out.println(p.toString());

        Livro l = livroRepository.findById(p.getLivros().get(0).getId()).get();

        List<Livro> livros = new ArrayList<>();

        livros.add(l);

        p.setLivros(livros);

        pedidoRepository.save(p);


        //Pedido pedido = this.service.create(Pedido.DtoRequest.convertToEntity(p, mapper));
        //Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(pedido, mapper);

        //response.generateLinks(pedido.getId());

        return "new Pedido.DtoResponse()";
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
