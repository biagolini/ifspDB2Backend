package com.loja.jogos.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbltypeplatform")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypePlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtypeplatform")
    private Long id;

    @Column(name = "dsdescription")
    private String description;

    @Column(name = "stactive")
    private Boolean isActive;

}
