package com.loja.jogos.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblTypePlatform")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypePlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypePlatform")
    private Long id;

    @Column(name = "dsDescription")
    private String description;

    @Column(name = "stActive")
    private Boolean isActive;

}
