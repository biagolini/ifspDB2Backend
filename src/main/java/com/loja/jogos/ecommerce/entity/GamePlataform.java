package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "tblGamePlataform")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamePlataform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGamePlataform")
    private Long id;

    @JoinColumn(name = "idGame")
    @ManyToOne
    private Game game;

    @JoinColumn(name = "idTypePlatform")
    @ManyToOne
    private TypePlatform platform;

    @Column(name = "stActive")
    private Boolean isActive;

}
