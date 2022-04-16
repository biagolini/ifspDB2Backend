package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "tblgameplataform")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamePlataform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgameplataform")
    private Long id;

    @Column(name = "idgame")
    private Long game;

    @Column(name = "isvideo")
    private Boolean isVideo;

    @Column(name = "dsurl")
    private String url;

    @Column(name = "stactive")
    private Boolean isActive;

}
