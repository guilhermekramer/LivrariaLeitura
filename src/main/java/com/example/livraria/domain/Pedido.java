package com.example.livraria.domain;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends AbstractEntity {
    Livro livro;
    Cliente cliente;


    public class DtoRequest{

        Livro livro;
        Cliente cliente;

        public static Pedido convertToPedido(DtoRequest dto, ModelMapper mapper ){
            return mapper.map(dto, Pedido.class);
        }
    }

    public class DtoResponse
}
