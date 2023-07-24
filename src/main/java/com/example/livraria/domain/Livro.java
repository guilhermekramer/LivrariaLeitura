package com.example.livraria.domain;

import com.example.livraria.controller.LivroController;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE livro SET deleted_At = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Livro extends AbstractEntity {
    Long id;
    String nome;
    String autor;
    Integer anoCriacao;
    Integer valor;
    String genero;

    public void partialUpdate(AbstractEntity e) {
        if (e instanceof Livro livro) {
            this.nome = livro.nome;
            this.autor = livro.autor;
            this.anoCriacao = livro.anoCriacao;
            this.valor = livro.valor;
            this.genero = livro.genero;
        }
    }


    //Classe que manipula o DTO Request
    //Método que converte DTO em Livro
    @Data
    public static class DtoRequest {
        Long id;
        @NotBlank(message = "É necessário inserir um nome para o Livro")
        String nome;
        @NotBlank(message = "É necessário inserir um autor para o Livro")
        String autor;
        Integer anoCriacao;
        @NotNull(message = "É necessário inserir um valor para o Livro")
        Integer valor;
        String genero;




        public static Livro convertToEntity (DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Livro.class );
        }

    }


    //Classe que manipula o DTO Response
    //Método que converte Livro em DTO
    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse> {
        Long id;
        String nome;
        String autor;
        Integer anoCriacao;
        Integer valor;
        String genero;

        public static DtoResponse convertToDto (Livro l, ModelMapper mapper){
            return mapper.map(l, DtoResponse.class);
        }

        public void generateLinks(Long id){
            add(linkTo(LivroController.class).slash(id).withSelfRel());
            add(linkTo(LivroController.class).withRel("livro"));
            add(linkTo(LivroController.class).slash(id).withRel("delete"));
        }

    }

}
