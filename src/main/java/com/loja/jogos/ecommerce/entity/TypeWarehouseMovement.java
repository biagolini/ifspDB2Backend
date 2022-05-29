package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblTypeWarehouseMovement")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypeWarehouseMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeWarehouseMovement")
    private Long id;

    @Column(name = "dsDescriptionEN")
    private String descriptionEn;

    @Column(name = "dsDescriptionPT")
    private String descriptionPt;

    @Column(name = "stActive")
    private Boolean isActive;


}
