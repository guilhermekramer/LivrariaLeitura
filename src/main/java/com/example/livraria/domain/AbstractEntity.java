package com.example.livraria.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@RequiredArgsConstructor
@Data
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDateTime deletedAt;
    LocalDateTime createdAt;
    LocalDateTime atualizadoAt;


    public <E extends AbstractEntity> void partialUpdate(E updatedEntity) {
    }
}
