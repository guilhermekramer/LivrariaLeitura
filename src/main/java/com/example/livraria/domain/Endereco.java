package com.example.livraria.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@SQLDelete(sql = "UPDATE endereco SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
//@Where(clause = "deleted_at is null")
//@SQLInsert(sql = "INSERT INTO endereco (created_at) VALUES (CURRENT_TIMESTAMP)")
//@SQLUpdate(sql = "UPDATE endereco SET atualizado_at")
//@Table( name = "endereco")
public class Endereco extends AbstractEntity implements Serializable {
    String bairro;
    String rua;
    Integer numero;

}
