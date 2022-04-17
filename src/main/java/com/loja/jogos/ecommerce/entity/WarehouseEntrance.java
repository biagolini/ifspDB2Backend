package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblWarehouseEntrance")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntrance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWarehouseEntrance")
    private Long id;

    @Column(name = "idGamePlataform")
    private Long gamePlataform;

    @Column(name = "dsQuantity")
    private Long quantity;

    @Column(name = "stActive")
    private Boolean isActive;

}
