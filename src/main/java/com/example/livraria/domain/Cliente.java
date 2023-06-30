package com.example.livraria.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

}
