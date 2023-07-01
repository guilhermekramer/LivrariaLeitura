package com.example.livraria.domain;

import com.example.livraria.controller.ClienteController;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import com.example.livraria.domain.Endereco;

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


