package com.example.livraria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.livraria.domain.Livro;
import com.example.livraria.service.LivroService;
@RestController
@RequestMapping("/livro")
public class LivroController {

    LivroService service;
    ModelMapper mapper;

    public LivroController(LivroService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro.DtoResponse create(@RequestBody Livro.DtoRequest p){

        Livro livro = this.service.create(Livro.DtoRequest.convertToEntity(p, mapper));

        Livro.DtoResponse response = Livro.DtoResponse.convertToDto(livro, mapper);
        response.generateLinks(Livro.getId());

        return response;
    }

    @GetMapping
    public List<Livro.DtoResponse> list(){

        return this.service.list().stream().map(
                elementoAtual -> {
                    Livro.DtoResponse response = Livro.DtoResponse.convertToDto(elementoAtual, mapper);
                    response.generateLinks(elementoAtual.getId());
                    return response;
                }).toList();
    }

    @GetMapping("{id}")
    public Livro.DtoResponse getById(@PathVariable Long id){

        Livro livro = this.service.getById(id);
        Livro.DtoResponse response = Livro.DtoResponse.convertToDto(livro, mapper);
        response.generateLinks(Livro.getId());

        return response;
    }
    @PutMapping("{id}")
    public Livro update(@RequestBody Livro p, @PathVariable Long id){
        return this.service.update(p, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}

