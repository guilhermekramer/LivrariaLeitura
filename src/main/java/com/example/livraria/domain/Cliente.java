package com.example.livraria.domain;

import com.example.livraria.controller.ClienteController;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends AbstractEntity{
    String nome;
    Integer idade;
    String cpf;
    String email;
    Boolean admin = false;



    //Classe Que manipula o objeto DTORequest
    //Método que converte DTO em Cliente
    @Data
    public static class DtoRequest {
        String nome;
        String cpf;
        String email;
        Integer idade;
        //Endereco endereco;
        public static Cliente convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Cliente.class);
        }

    }


    //Classe Que manipula o objeto DTOResponse
    //Método que converte Cliente em DTO
    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse> {

        @NotBlank(message = "É necessário inserir um nome para o Cliente")
        String nome;
        @NotNull(message = "É necessário inserir uma idade para o Cliente")
        Integer idade;
        @NotBlank(message = "É necessário inserir um cpf para o Cliente")
        String cpf;
        @Email
        String email;


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


