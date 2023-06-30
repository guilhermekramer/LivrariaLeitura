package com.example.livraria.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

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
    Endereco endereco;
    Boolean admin = false;


    @Data
    public static class DtoRequest {
        @NotBlank(message = "Usuário com nome em branco")
        String nome;
        String cpf;
        @Email (message = "Email")
        String email;
        Integer idade;
        Endereco endereco;
        Boolean admin = false;


        public static Cliente convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Cliente.class);
        }

    }

    public static class DtoResponse {
        String nome;
        String email;
        Integer idade;

        public static DtoResponse convertToDto(Cliente c, ModelMapper mapper){
            return mapper.map(c, DtoResponse.class);
        }



        public void generateLinks(Long id){
            add(linkTo(Cliente.class).slash(id).withSelfRel());  //parei aqui, acho que precisa implementar controller e serviços antes de prosseguir
            add(linkTo(Cliente.class).withRel("cliente"));
            add(linkTo(Cliente.class).slash(id).withRel("delete"));
        }
    }


}


