package com.example.livraria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.livraria.domain.Endereco;
import com.example.livraria.service.EnderecoService;

@RestController
@RequestMapping("/Endereco")
public class EnderecoController {

    EnderecoService service;
    ModelMapper mapper;

    public EnderecoController(EnderecoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco.DtoResponse create(@RequestBody Endereco.DtoRequest p){

        Endereco endereco = this.service.create(Endereco.DtoRequest.convertToEntity(p, mapper));

        Endereco.DtoResponse response = Endereco.DtoResponse.convertToDto(endereco, mapper);
        response.generateLinks(Endereco.getId());

        return response;
    }

    @GetMapping
    public List<Endereco.DtoResponse> list(){

        return this.service.list().stream().map(
                elementoAtual -> {
                    Endereco.DtoResponse response = Endereco.DtoResponse.convertToDto(elementoAtual, mapper);
                    response.generateLinks(elementoAtual.getId());
                    return response;
                }).toList();
    }

    @GetMapping("{id}")
    public Endereco.DtoResponse getById(@PathVariable Long id){

        Endereco endereco = this.service.getById(id);
        Endereco.DtoResponse response = Endereco.DtoResponse.convertToDto(endereco, mapper);
        response.generateLinks(Endereco.getId());

        return response;
    }
    @PutMapping("{id}")
    public Endereco update(@RequestBody Endereco p, @PathVariable Long id){
        return this.service.update(p, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}
