package com.example.livraria.domain;

import com.example.livraria.controller.ClienteController;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
@SQLDelete(sql = "UPDATE cliente SET deleted_At = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Cliente extends AbstractEntity implements Serializable {


    String nome;
    Integer idade;
    String cpf;
    String email;
    Boolean admin = false;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "fk_endereco")
    Endereco endereco;

    //@OneToMany(mappedBy="cliente", fetch = FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    //List<Pedido> pedidos;

    /*
    public void addPedido(Pedido novoPedido){
        pedidos.add(novoPedido);
        novoPedido.setCliente(this);
    }
    public void removePedido(Pedido removePedido){
        pedidos.remove(removePedido);
        removePedido.setCliente(null);
    }*/

    //Classe Que manipula o objeto DTORequest
    //Método que converte DTO em Cliente

    @Data
    public static class DtoRequest {
        @NotBlank(message = "É necessário inserir um nome para o Cliente")
        String nome;
        @NotNull(message = "É necessário inserir uma idade para o Cliente")
        Integer idade;
        @NotBlank(message = "É necessário inserir um cpf para o Cliente")
        String cpf;
        @Email
        String email;
        Endereco endereco;

        public static Cliente convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Cliente.class);
        }

    }


    //Classe Que manipula o objeto DTOResponse
    //Método que converte Cliente em DTO

    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse> {

        String nome;
        Integer idade;
        String cpf;
        String email;
        Endereco endereco;


        public static DtoResponse convertToDto(Cliente c, ModelMapper mapper){
            return mapper.map(c, DtoResponse.class);
        }

        public void generateLinks(Long id){
            add(linkTo(ClienteController.class).slash(id).withSelfRel());
            add(linkTo(ClienteController.class).withRel("cliente"));
            add(linkTo(ClienteController.class).slash(id).withRel("delete"));
        }
    }

}


