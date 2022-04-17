package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblTypeGenre")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeGenre")
    private Long id;

    @Column(name = "dsDescriptionEN")
    private String descriptionEn;

    @Column(name = "dsDescriptionPT")
    private String descriptionPt;

    @Column(name = "stActive")
    private Boolean isActive;

}
