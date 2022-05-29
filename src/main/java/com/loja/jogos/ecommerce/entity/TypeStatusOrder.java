package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblTypeStatusOrder")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypeStatusOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeStatusOrder")
    private Long id;

    @Column(name = "dsDescriptionEN")
    private String descriptionEn;

    @Column(name = "dsDescriptionPT")
    private String descriptionPt;

    @Column(name = "stActive")
    private Boolean isActive;

}

