package com.example.livraria.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;

@MappedSuperclass
@RequiredArgsConstructor
@Data
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonIgnore
    LocalDateTime deletedAt;
    @JsonIgnore
    LocalDateTime createdAt;
    @JsonIgnore
    LocalDateTime atualizadoAt;


    public abstract void partialUpdate(AbstractEntity e);
}
