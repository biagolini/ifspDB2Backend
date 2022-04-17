package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblMedia")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMedia")
    private Long id;

    @Column(name = "idGame")
    private Long game;

    @Column(name = "isVideo")
    private Boolean isVideo;

    @Column(name = "dsUrl")
    private String url;

    @Column(name = "stActive")
    private Boolean isActive;

}

