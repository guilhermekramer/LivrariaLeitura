package com.example.livraria.controller;

import com.example.livraria.service.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.livraria.domain.Cliente;
import com.example.livraria.service.ClienteService;


import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    ClienteService service;

    ModelMapper mapper;


    public ClienteController(ClienteService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente.DtoResponse create(@RequestBody Cliente.DtoRequest c){
        Cliente cliente = this.service.create(Cliente.DtoRequest.convertToEntity(c, mapper));
        Cliente.DtoResponse response = Cliente.DtoResponse.convertToDto(cliente, mapper);
        response.generateLinks(cliente.getId());

        return response;
    }

    @GetMapping
    public List<Cliente.DtoResponse> list(){

        return this.service.list().stream().map(
                elementoAtual -> {
                    Cliente.DtoResponse response = Cliente.DtoResponse.convertToDto(elementoAtual, mapper);
                    response.generateLinks(elementoAtual.getId());
                    return response;
                }).toList();
    }

    @GetMapping("{id}")
    public Cliente.DtoResponse getById(@PathVariable Long id){
        Cliente cliente = this.service.getById(id);
        Cliente.DtoResponse response = Cliente.DtoResponse.convertToDto(cliente, mapper);
        response.generateLinks(cliente.getId());
        return response;
    }

    @PutMapping("{id}")
    public Cliente.DtoResponse update(@RequestBody Cliente.DtoRequest dtoRequest, @PathVariable Long id){
        Cliente c = Cliente.DtoRequest.convertToEntity(dtoRequest, mapper);
        Cliente.DtoResponse response = Cliente.DtoResponse.convertToDto(this.service.update(c, id), mapper);
        response.generateLinks(id);
        return response;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}
