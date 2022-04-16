package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblhighlight")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Highlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddestaque")
    private Long id;

    @Column(name = "idgame")
    private Long game;

    @Column(name = "dspublicationdate")
    private LocalDate publicationDate;

    @Column(name = "stactive")
    private Boolean isActive;

}

