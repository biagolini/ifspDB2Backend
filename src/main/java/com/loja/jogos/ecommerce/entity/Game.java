package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "tblgame")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgame")
    private Long id;

    @Column(name = "dsname")
    private String name;

    @Column(name = "dsreleasedate")
    private LocalDate releaseDate;

    @Column(name = "idtypegenre")
    private Long genre;

    @Column(name = "idpublisher")
    private Long publisher;

    @Column(name = "dscover")
    private String cover;

    @Column(name = "stactive")
    private Boolean isActive;

}
