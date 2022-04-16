package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblmedia")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmedia")
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

