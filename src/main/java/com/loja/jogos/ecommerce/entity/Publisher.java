package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblpublisher")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpublisher")
    private Long id;

    @Column(name = "dsdescription")
    private String description;

    @Column(name = "dswikipedialink")
    private String mediaLink;

    @Column(name = "stactive")
    private Boolean isActive;

}

