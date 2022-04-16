package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblprice")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprice")
    private Long id;

    @Column(name = "idgameplataform")
    private Long gamePlataform;

    @Column(name = "dsvalue")
    private double value;

    @Column(name = "stactive")
    private Boolean isActive;

}

