package com.example.livraria.domain;



import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import java.io.Serializable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE endereco SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")

public class Endereco extends AbstractEntity implements Serializable {
    String bairro;
    String rua;
    Integer numero;


    @Override
    public void partialUpdate(AbstractEntity e) {
        if (e instanceof Endereco endereco){
            this.bairro = endereco.bairro;
            this.rua = endereco.rua;
            this.numero = endereco.numero;
        }
    }
//    @Data
//    public static class DtoRequest {
//        @NotBlank(message = "É necessário inserir um bairro")
//        String bairro;
//        @NotBlank(message = "É necessário inserir uma rua ")
//        String rua;
//        @NotNull(message = "É necessário inserir um numero para endereco")
//        Integer numero;
//
//    }
//    public static Endereco convertToEntity(DtoRequest dto, ModelMapper mapper){
//        return mapper.map(dto, Endereco.class);
//    }
//    }
//
//    @Data
//    public static class DtoResponse extends RepresentationModel<DtoResponse> {
//        String bairro;
//        String rua;
//        Integer numero;
//    }
//
//    public static DtoResponse convertToDto(Endereco e, ModelMapper mapper){
//        return mapper.map(e, DtoResponse.class);
//    }
////    public void generateLinks(Long id){
////        add(linkTo(ClienteController.class).slash(id).withSelfRel());
////        add(linkTo(ClienteController.class).withRel("cliente"));
////        add(linkTo(ClienteController.class).slash(id).withRel("delete"));
////    }
}
