package com.example.livraria.domain;



import com.example.livraria.controller.PedidoController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;



import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends AbstractEntity {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "livro_pedido", joinColumns = { @JoinColumn(name = "pedido_id", referencedColumnName = "id") },
    inverseJoinColumns = {@JoinColumn(name = "livro_id", referencedColumnName = "id") })
    List<Livro> livros = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;


    @Data
    public class DtoRequest{
        @NotNull
        List<Livro> livros;
        @NotNull
        Cliente cliente;
        public static Pedido convertToEntity(DtoRequest dto, @NotNull ModelMapper mapper ){
            return mapper.map(dto, Pedido.class);
        }
    }
    @Data
    public class DtoResponse extends RepresentationModel<Pedido.DtoResponse>{
        List<Livro> livros;
        Cliente cliente;

        public static DtoResponse convertToDto(Pedido p, ModelMapper mapper){
            return mapper.map(p, DtoResponse.class);

        }

        public void generateLinks(Long id){
            add(linkTo(PedidoController.class).slash(id).withSelfRel());
            add(linkTo(PedidoController.class).withRel("pedido"));
            add(linkTo(PedidoController.class).slash(id).withRel("delete"));
        }
    }
}
