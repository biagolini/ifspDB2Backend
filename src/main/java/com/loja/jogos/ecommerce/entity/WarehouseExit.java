package com.loja.jogos.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblWarehouseExit")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseExit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWarehouseExit")
    private Long id;

    @Column(name = "idGamePlatform")
    private Long gamePlatform;

    @Column(name = "idTypeWarehouseMovement")
    private Long typeWarehouseMovement;

    @Column(name = "dsQuantity")
    private Long quantity;

    @Column(name = "dsMovementDateTime")
    private LocalDateTime exitDateTime;


}