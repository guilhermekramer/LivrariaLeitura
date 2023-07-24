package com.example.livraria.domain;



import com.example.livraria.controller.PedidoController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
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
@SQLDelete(sql = "UPDATE pedido SET deleted_At = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Pedido extends AbstractEntity {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "livro_pedido", joinColumns = { @JoinColumn(name = "pedido_id", referencedColumnName = "id") },
    inverseJoinColumns = {@JoinColumn(name = "livro_id", referencedColumnName = "id") })
    List<Livro> livros = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;

    @Override
    public void partialUpdate(AbstractEntity e) {

    }


    @Data
    public static class DtoRequest{
        Long id;
        Long clienteId;
        List<Long> livrosId;

        public static Pedido convertToEntity(DtoRequest dto, ModelMapper mapper ){
            return mapper.map(dto, Pedido.class);
        }
    }




    @Data
    public static class  DtoResponse extends RepresentationModel<Pedido.DtoResponse>{
        long id;
        Long clienteId;
        List<Livro.DtoResponse> livros;

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
