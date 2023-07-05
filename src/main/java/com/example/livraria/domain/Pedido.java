package com.example.livraria.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends AbstractEntity {

    @ManyToMany
    @JoinTable(name = "livro_pedido", joinColumns = { @JoinColumn(name = "pedido_id", referencedColumnName = "id") },
    inverseJoinColumns = {@JoinColumn(name = "livro_id") })
    List<Livro> livros;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;


    @Data
    public class DtoRequest{

        List<Livro> livros;
        Cliente cliente;
        public static Pedido convertToEntityPedido(DtoRequest dto, ModelMapper mapper ){
            return mapper.map(dto, Pedido.class);
        }
    }
    @Data
    public class DtoResponse {
        List<Livro> livros;
        Cliente cliente;

        public static DtoResponse convertToDtoPedido(Pedido pedido, ModelMapper mapper){
            return mapper.map(pedido, DtoResponse.class);

        }
    }
}
