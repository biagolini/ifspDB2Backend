package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblHighlight")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Highlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHighlight")
    private Long id;

    @Column(name = "idGame")
    private Long game;

    @Column(name = "dsHighlightHeader")
    private String header;

    @Column(name = "dsHighlightImage")
    private String cover;

    @Column(name = "dsHighlightDescription")
    private String description;

    @Column(name = "dsPublicationDate")
    private LocalDate publicationDate;

    @Column(name = "stactive")
    private Boolean isActive;

}

