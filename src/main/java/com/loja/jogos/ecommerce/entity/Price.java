package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblPrice")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrice")
    private Long id;

    @Column(name = "idGamePlataform")
    private Long gamePlataform;

    @Column(name = "dsValue")
    private double value;

    @Column(name = "stActive")
    private Boolean isActive;

}

