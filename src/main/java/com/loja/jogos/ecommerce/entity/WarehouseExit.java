package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.WarehouseMovementForm;
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

    @JoinColumn(name = "idGamePlatform")
    @ManyToOne
    private GamePlatform gamePlatform;

    @Column(name = "idTypeWarehouseMovement")
    private Long typeWarehouseMovement;

    @Column(name = "dsQuantity")
    private Long quantity;

    @Column(name = "dsMovementDateTime")
    private LocalDateTime exitDateTime;

    public WarehouseExit(GamePlatform gamePlatform, WarehouseMovementForm form) {
        this.gamePlatform = gamePlatform;
        this.typeWarehouseMovement = form.getIdTypeWarehouseMovement();
        this.quantity = form.getQuantity();
        this.exitDateTime = LocalDateTime.now();
    }

}