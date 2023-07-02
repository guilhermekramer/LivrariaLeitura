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


    @Data
    public class DtoRequest{

        Livro livro;
        Cliente cliente;

        public static Pedido convertToEntityPedido(DtoRequest dto, ModelMapper mapper ){
            return mapper.map(dto, Pedido.class);
        }
    }

    @Data
    public class DtoResponse {
        Livro livro;
        Cliente cliente;

        public static DtoResponse convertToDtoPedido(Pedido pedido, ModelMapper mapper){
            return mapper.map(pedido, DtoResponse.class);

        }
    }
}
