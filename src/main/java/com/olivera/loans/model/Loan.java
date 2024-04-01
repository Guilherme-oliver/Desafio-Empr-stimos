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
@Table(name = "tb_loan")
public class Loan {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    private String type;
    private Integer interestRate;
}