package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblTypeState")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeState")
    private Long id;

    @Column(name = "dsAbbreviation")
    private String description;

    @Column(name = "dsName")
    private String name;

    @Column(name = "stActive")
    private Boolean isActive;

}

