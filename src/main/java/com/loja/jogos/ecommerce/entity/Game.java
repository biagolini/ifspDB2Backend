package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "tblGame")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGame")
    private Long id;

    @Column(name = "dsName")
    private String name;

    @Column(name = "dsReleaseDate")
    private LocalDate releaseDate;

    @Column(name = "idTypeGenre")
    private Long genre;

    @Column(name = "idPublisher")
    private Long publisher;

    @Column(name = "dsCover")
    private String cover;

    @Column(name = "stActive")
    private Boolean isActive;

}
