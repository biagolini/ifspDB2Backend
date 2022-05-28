package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblUserProfile")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUserProfile")
    private Long id;

    @Column(name = "dsDescription")
    private String description;

    @Column(name = "stCreateLink")
    private Boolean isAllowCreateLink;

}
