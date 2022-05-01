package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "tblGamePlatform")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamePlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGamePlatform")
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
