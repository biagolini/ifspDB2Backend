package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblPublisher")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublisher")
    private Long id;

    @Column(name = "dsDescription")
    private String description;

    @Column(name = "dsWikipediaLink")
    private String mediaLink;

    @Column(name = "stActive")
    private Boolean isActive;

}

