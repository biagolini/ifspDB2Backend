package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbltypestate")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtypestate")
    private Long id;

    @Column(name = "dsabbreviation")
    private String description;

    @Column(name = "dsname")
    private String name;

    @Column(name = "stactive")
    private Boolean isActive;

}

