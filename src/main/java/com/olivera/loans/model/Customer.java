package com.olivera.loans.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tb_costumer")
public class Customer {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    private Integer age;
    private Long cpf;
    private String name;
    private Double income;
    private String location;
}