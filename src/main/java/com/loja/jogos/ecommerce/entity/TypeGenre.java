package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbltypegenre")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtypegenre")
    private Long id;

    @Column(name = "dsdescriptionen")
    private String descriptionEn;

    @Column(name = "dsdescriptionpt")
    private String descriptionPt;

    @Column(name = "stactive")
    private Boolean isActive;

}
