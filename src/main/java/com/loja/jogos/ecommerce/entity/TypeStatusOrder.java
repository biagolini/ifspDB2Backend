package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbltypestatusorder")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeStatusOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtypestatusorder")
    private Long id;

    @Column(name = "dsdescriptionen")
    private String descriptionEn;

    @Column(name = "dsdescriptionpt")
    private String descriptionPt;

    @Column(name = "stactive")
    private Boolean isActive;

}

